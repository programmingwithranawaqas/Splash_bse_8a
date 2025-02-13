package com.example.splash_bse_8a;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class GetUserData extends AppCompatActivity {

    TextInputEditText etUsername, etPhone, etAddress, etUrl;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_user_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btnCancel.setOnClickListener((v)->{
            setResult(RESULT_CANCELED);
            finish();
        });
        btnSubmit.setOnClickListener((v)->{
            String username = etUsername.getText().toString();
            String address = etAddress.getText().toString();
            String url = etUrl.getText().toString();
            String phone = etPhone.getText().toString();

            Intent i = new Intent();
            i.putExtra("username", username);
            i.putExtra("address", address);
            i.putExtra("url", url);
            i.putExtra("phone", phone);

            setResult(RESULT_OK, i);
            finish();
        });
    }

    private void init()
    {
        etAddress = findViewById(R.id.tietAddress);
        etPhone = findViewById(R.id.tietPhone);
        etUrl = findViewById(R.id.tietUrl);
        etUsername = findViewById(R.id.tietUsername);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
    }
}