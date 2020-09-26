package com.example.newsapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplication.InfoActivity;
import com.example.newsapplication.R;
import com.example.newsapplication.models.Article;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> implements OnItemClickListener {
    List<Article> articlesList = new ArrayList<>();
     Context context;

    public void setAdapterData(List<Article> list) {
        this.articlesList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.news_list, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        viewHolder.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.onBind(articlesList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra("url", articlesList.get(position).getUrl());
        context.startActivity(intent);
    }
}
