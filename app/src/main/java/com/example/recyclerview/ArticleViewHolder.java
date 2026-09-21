package com.example.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvContent, tvViews;
    ImageView ivCover;
    ArticleAdapter adapter;

    public ArticleViewHolder(@NonNull View itemView, ArticleAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvContent = itemView.findViewById(R.id.tvContent);
        tvViews = itemView.findViewById(R.id.tvViews);
        ivCover = itemView.findViewById(R.id.ivCover);
    }
}
