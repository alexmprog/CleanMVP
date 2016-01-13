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
import com.renovavision.cleanmvp.model.Image;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexmprog on 22.12.2015.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {

    @NonNull
    private List<Image> items;

    public ImagesAdapter(@NonNull List<Image> items) {
        this.items = items;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.image_layout,
                        parent,
                        false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final Image image = items.get(position);
        holder.tweet.setText(image.getRetweetCount());
        if (!TextUtils.isEmpty(image.getMediaUrl())) {
            holder.media.setVisibility(View.VISIBLE);
            Picasso.with(holder.media.getContext()).load(image.getMediaUrl()).into(holder.media);
        } else {
            holder.media.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Image getItem(int position) {
        return items.get(position);
    }

    public void setItems(@NonNull List<Image> imageList) {
        items = imageList;
        notifyDataSetChanged();
    }

    final static class ImageViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tweet)
        TextView tweet;
        @Bind(R.id.media)
        ImageView media;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
