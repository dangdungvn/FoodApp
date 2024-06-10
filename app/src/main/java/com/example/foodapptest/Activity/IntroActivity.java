package com.example.foodapptest.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodapptest.R;
import com.example.foodapptest.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariables();
        getWindow().setStatusBarColor(Color.parseColor("#FFE4B5"));
    }

    private void setVariables() {
        binding.loginBtn.setOnClickListener(v -> {
            if(mAuth.getCurrentUser() != null){
                startActivity(new Intent(IntroActivity.this,MainActivity.class));
            } else {
                startActivity(new Intent(IntroActivity.this,LoginActivity.class));
            }
        });

        binding.SignupBtn.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this,SignupActivity.class)));
    }
}