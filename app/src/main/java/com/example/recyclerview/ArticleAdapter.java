package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private Context context;
    private List<Article> articleList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Article article, int position);
    }

    public ArticleAdapter(Context context, List<Article> articleList, OnItemClickListener listener) {
        this.context = context;
        this.articleList = articleList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_item_layout, parent, false);
        return new ArticleViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvContent.setText(article.getContent());
        holder.tvViews.setText("Views: " + article.getViews());
        holder.ivCover.setImageResource(article.getImgCover());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(article, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
