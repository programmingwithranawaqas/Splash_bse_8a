package com.example.splash_bse_8a;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rl_bars;
    Animation top_to_bottom, left_to_right, bottom_to_top;
    ImageView ivLogo;
    TextView tvSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rl_bars = findViewById(R.id.rl_bars);
        ivLogo = findViewById(R.id.ivLogo);
        tvSlogan = findViewById(R.id.tvSlogan);

        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom_animation);
        left_to_right = AnimationUtils.loadAnimation(this, R.anim.left_to_right_animation);
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        rl_bars.setAnimation(top_to_bottom);
        ivLogo.setAnimation(left_to_right);
        tvSlogan.setAnimation(bottom_to_top);

        new Handler()
                .postDelayed(()->{
                    startActivity(new Intent(MainActivity.this, Home.class));
                    finish();
                }, 10000);

    }

}