package com.renovavision.cleanmvp.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.model.Image;
import com.renovavision.cleanmvp.presenters.ImagePresenter;
import com.renovavision.cleanmvp.presenters.impl.ImagePresenterImpl;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexmprog on 22.12.2015.
 */
public class ImageActivity extends AppCompatActivity implements com.renovavision.cleanmvp.ui.views.ImageView {

    @Bind(R.id.container_inner_item)
    RelativeLayout mInnerContainer;

    @Bind(R.id.title)
    TextView mTitleView;

    @Bind(R.id.tweet)
    TextView mTweetView;

    @Bind(R.id.media)
    ImageView mImageView;

    @NonNull
    private ImagePresenter mImagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);

        ViewCompat.setTransitionName(mInnerContainer, TRANSITION_SHARED_ELEMENT);

        mImagePresenter = new ImagePresenterImpl(this);
        mImagePresenter.loadImage(getIntent().getExtras());
    }

    @NonNull
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public void showImage(@NonNull Image image) {
        Picasso.with(this).load(image.getMediaUrl()).into(mImageView);
        mTweetView.setText(image.getTweetCount());
        mTitleView.setText(image.getTitle());
    }
}
