package com.renovavision.cleanmvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.model.Article;
import com.twitter.sdk.android.core.TwitterCore;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexmprog on 22.12.2015.
 */
public class ArticleActivity extends AppCompatActivity {
    public static final String EXTRA_ARTICLE = "article";
    public static final String TRANSITION_SHARED_ELEMENT = "title";

    @Bind(R.id.card_view)
    CardView cardView;
    @Bind(R.id.title)
    TextView titleView;
    @Bind(R.id.tweet)
    TextView tweetView;
    @Bind(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        Article article = extras.getParcelable(EXTRA_ARTICLE);

        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);

        View innerContainer = cardView.findViewById(R.id.container_inner_item);
        ViewCompat.setTransitionName(innerContainer, TRANSITION_SHARED_ELEMENT);
        if (article != null) {
            titleView.setText(article.getTitle());
            tweetView.setText(article.getTweetCount());
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(article.getUrl());
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        TwitterCore.getInstance().logOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
