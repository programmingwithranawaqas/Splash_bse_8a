package com.example.splash_bse_8a;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Home extends AppCompatActivity {

    // hooks
    RadioGroup rgGender;
    Button btnSbmit, btnGetData;
    TextView tvResult;

    String username, address, phone, url;


    // hooks for profile picture
    ImageView ivProfilePic;
    FloatingActionButton fabSetProfilePic;
    ActivityResultLauncher<Intent> getImageLauncher;

    ActivityResultLauncher<Intent> getDataLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btnSbmit.setOnClickListener((v)->{
            int idGender = rgGender.getCheckedRadioButtonId();
            if(idGender!=-1)
            {
                RadioButton rbOption = findViewById(idGender);
                String textOfRadioButton = rbOption.getText().toString();
                tvResult.setText(textOfRadioButton);
            }
            else
            {
                Toast.makeText(this, "Select gender", Toast.LENGTH_SHORT).show();
            }
        });

        fabSetProfilePic.setOnClickListener((v)->{
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            getImageLauncher.launch(i);
        });

        btnGetData.setOnClickListener((v)->{
            Intent i = new Intent(this, GetUserData.class);
            getDataLauncher.launch(i);
        });

    }

    private void init()
    {
        rgGender = findViewById(R.id.rgGender);
        btnSbmit = findViewById(R.id.btnSubmit);
        btnGetData = findViewById(R.id.btnGetData);
        tvResult = findViewById(R.id.tvResult);
        ivProfilePic = findViewById(R.id.ivProfilePic);
        fabSetProfilePic = findViewById(R.id.fab_setProfilePic);

        // launcher code
//        getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult o) {
//
//                    }
//                });

        getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->{
                      if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                      {
                          Uri image = result.getData().getData();
                          ivProfilePic.setImageURI(image);
                      }
                      else
                      {
                          Toast.makeText(this, "Select the image", Toast.LENGTH_SHORT).show();
                      }
                });

        getDataLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->{
                      if(result.getResultCode() == RESULT_CANCELED)
                      {
                          Toast.makeText(this, "Data not entered by user", Toast.LENGTH_SHORT).show();
                      }
                      else if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                      {
                          Intent dataIntent = result.getData();
                          username = dataIntent.getStringExtra("username");
                          phone = dataIntent.getStringExtra("phone");
                          address = dataIntent.getStringExtra("address");
                          url = dataIntent.getStringExtra("url");
                          Toast.makeText(this, username+"\n"+phone+"\n"+address+"\n"+url, Toast.LENGTH_SHORT).show();

                      }
                });

    }
}