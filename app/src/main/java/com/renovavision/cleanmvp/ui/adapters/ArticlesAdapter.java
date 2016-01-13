package com.renovavision.cleanmvp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexmprog on 22.12.2015.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    @NonNull
    private List<Article> items;

    public ArticlesAdapter(@NonNull List<Article> items) {
        this.items = items;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.article_layout,
                        parent,
                        false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        final Article article = items.get(position);
        holder.title.setText(article.getTitle());
        holder.tweet.setText(article.getTweetCount());
        if (!TextUtils.isEmpty(article.getMediaUrl())) {
            holder.media.setVisibility(View.VISIBLE);
            Picasso.with(holder.media.getContext()).load(article.getMediaUrl()).into(holder.media);
        } else {
            holder.media.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Article getItem(int position) {
        return items.get(position);
    }

    public void setItems(@NonNull List<Article> articleList) {
        items = articleList;
        notifyDataSetChanged();
    }

    final static class ArticleViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.tweet)
        TextView tweet;
        @Bind(R.id.media)
        ImageView media;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
