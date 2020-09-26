package com.example.newsapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapplication.R;
import com.example.newsapplication.models.Article;


public class NewsViewHolder extends RecyclerView.ViewHolder {
    private OnItemClickListener onItemClickListener;
    private int position;
    TextView titleTextView;
    ImageView news_img;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.title_news);
        news_img = itemView.findViewById(R.id.news_img);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void onBind(Article article, int position) {
        titleTextView.setText(article.getTitle());
        Glide.with(itemView.getContext()).load(article.getUrlToImage()).into(news_img);
        this.position = position;
    }
}
