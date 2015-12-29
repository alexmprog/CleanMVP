package com.renovavision.cleanmvp.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.entities.Image;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexmprog on 22.12.2015.
 */
public class ImageActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE = "image";
    public static final String TRANSITION_SHARED_ELEMENT = "content";

    @Bind(R.id.card_view)
    CardView cardView;
    @Bind(R.id.title)
    TextView titleView;
    @Bind(R.id.tweet)
    TextView tweetView;
    @Bind(R.id.media)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        Image image = extras.getParcelable(EXTRA_IMAGE);

        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);

        View innerContainer = cardView.findViewById(R.id.container_inner_item);
        ViewCompat.setTransitionName(innerContainer, TRANSITION_SHARED_ELEMENT);
        if (image != null) {
            Picasso.with(this).load(image.getMediaUrl()).into(imageView);
            tweetView.setText(image.getRetweetCount());
            titleView.setText(image.getTitle());
        } else {
            finish();
        }
    }
}
