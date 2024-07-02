package com.example.foodapptest.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapptest.Adapter.FoodListAdapter;
import com.example.foodapptest.Domain.Foods;
import com.example.foodapptest.R;
import com.example.foodapptest.databinding.ActivityListFoodsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFoodsActivity extends BaseActivity {
    ActivityListFoodsBinding binding;
    private RecyclerView.Adapter adapterListFood;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;
    private int locationId;
    private int timeId;
    private int priceId;


    public ListFoodsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityListFoodsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();
        initList();
        setVariables();
    }

    private void setVariables() {

    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();

        if ((categoryId == -1) && (locationId != -1) && (timeId != -1) && (priceId != -1) && !isSearch) {
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot issue : snapshot.getChildren()) {
                            Foods food = issue.getValue(Foods.class);
                            if (food.getLocationId() == locationId && food.getTimeId() == timeId && food.getPriceId() == priceId) {
                                list.add(food);
                            }
                        }
                        if (!list.isEmpty()) {
                            binding.foodListView.setLayoutManager(new GridLayoutManager(ListFoodsActivity.this, 2));
                            adapterListFood = new FoodListAdapter(list);
                            binding.foodListView.setAdapter(adapterListFood);
                        }
                        binding.progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            Query query;
            if (isSearch) {
                query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText + "\uf8ff");
            } else if ((categoryId != -1) && (locationId == -1) && (timeId == -1) && (priceId == -1)) {
                query = myRef.orderByChild("CategoryId").equalTo(categoryId);
            } else {
                query = myRef;
            }
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot issue : snapshot.getChildren()) {
                            list.add(issue.getValue(Foods.class));
                        }
                        if (!list.isEmpty()) {
                            binding.foodListView.setLayoutManager(new GridLayoutManager(ListFoodsActivity.this, 2));
                            adapterListFood = new FoodListAdapter(list);
                            binding.foodListView.setAdapter(adapterListFood);
                        }
                        binding.progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId", -1);
        categoryName = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);
        locationId = getIntent().getIntExtra("LocationId", -1);
        timeId = getIntent().getIntExtra("TimeId", -1);
        priceId = getIntent().getIntExtra("PriceId", -1);
        if (isSearch) {
            binding.titleTxt2.setText(searchText);
        } else if ((categoryId != -1) && (locationId == -1) && (timeId == -1) && (priceId == -1)) {
            binding.titleTxt2.setText(categoryName);
        } else if ((categoryId == -1) && (locationId != -1) && (timeId != -1) && (priceId != -1)) {
            binding.titleTxt2.setText("Phân Loại");
        } else {
            binding.titleTxt2.setText("Danh sách");
        }
        binding.btnBack.setOnClickListener(v -> finish());
    }
}