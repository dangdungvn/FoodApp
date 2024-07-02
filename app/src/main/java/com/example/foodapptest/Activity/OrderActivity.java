package com.example.foodapptest.Activity;


import android.content.Intent;
import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import com.example.foodapptest.databinding.ActivityOrderBinding;

public class OrderActivity extends BaseActivity {
    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();
    }


    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> {
            startActivity(new Intent(OrderActivity.this, MainActivity.class));
            finish();
        });
    }
}