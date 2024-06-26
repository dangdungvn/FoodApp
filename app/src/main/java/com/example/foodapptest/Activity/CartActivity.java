package com.example.foodapptest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapptest.Adapter.CartAdapter;
import com.example.foodapptest.Helper.ChangeNumberItemsListener;
import com.example.foodapptest.Helper.ManagmentCart;
import com.example.foodapptest.R;
import com.example.foodapptest.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private double tax;

    public CartActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart = new ManagmentCart(this);
        setVariable();
        caculateCart();
        initList();
    }

    private void initList() {
        if(managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.cardView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managmentCart.getListCart(), this, this::caculateCart);
        binding.cardView.setAdapter(adapter);
    }

    private void caculateCart() {
        double percentTax = 0.02; //percent 2%
        double delivery = 10; //dollar
        tax = (double) Math.round(managmentCart.getTotalFee() * percentTax * 100.0) / 100;
        double total = (double) Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = (double) Math.round(managmentCart.getTotalFee() * 100) / 100;
        binding.totalFeeTxt.setText(String.format("$%s", itemTotal));
        binding.taxTxt.setText(String.format("$%s", tax));
        binding.deliveryTxt.setText(String.format("$%s", delivery));
        binding.totalTxt.setText(String.format("$%s", total));
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
        binding.placeOrderBtn.setOnClickListener(v -> {
            managmentCart.clearCart();
            startActivity(new Intent(CartActivity.this, OrderActivity.class));
            finish();
        });
    }
}