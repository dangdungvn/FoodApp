package com.example.foodapptest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodapptest.Activity.ListFoodsActivity;
import com.example.foodapptest.Domain.Category;
import com.example.foodapptest.Domain.Foods;
import com.example.foodapptest.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        holder.titleTxt.setText(items.get(position).getName());

        if (position == 0) {
            holder.pic.setBackgroundResource(R.drawable.cat_0_background);
        } else if (position == 1) {
            holder.pic.setBackgroundResource(R.drawable.cat_1_background);
        } else if (position == 2) {
            holder.pic.setBackgroundResource(R.drawable.cat_2_background);
        } else if (position == 3) {
            holder.pic.setBackgroundResource(R.drawable.cat_3_background);
        } else if (position == 4) {
            holder.pic.setBackgroundResource(R.drawable.cat_4_background);
        } else if (position == 5) {
            holder.pic.setBackgroundResource(R.drawable.cat_5_background);
        } else if (position == 6) {
            holder.pic.setBackgroundResource(R.drawable.cat_6_background);
        } else if (position == 7) {
            holder.pic.setBackgroundResource(R.drawable.cat_7_background);
        }

        int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath()
                , "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ListFoodsActivity.class);
            intent.putExtra("CategoryId", items.get(position).getId());
            intent.putExtra("CategoryName", items.get(position).getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.catNameTxt);
            pic = itemView.findViewById(R.id.imgCat);
        }
    }
}
