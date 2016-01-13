package com.renovavision.cleanmvp.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.model.Article;
import com.renovavision.cleanmvp.presenters.ArticlePresenter;
import com.renovavision.cleanmvp.presenters.impl.ArticlePresenterImpl;
import com.renovavision.cleanmvp.ui.views.ArticleView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexmprog on 22.12.2015.
 */
public class ArticleActivity extends AppCompatActivity implements ArticleView {

    @Bind(R.id.container_inner_item)
    RelativeLayout mInnerContainer;

    @Bind(R.id.title)
    TextView mTitleView;

    @Bind(R.id.tweet)
    TextView mTweetView;

    @Bind(R.id.web_view)
    WebView mWebView;

    @Bind(R.id.progress)
    ProgressBar mProgressBar;

    @NonNull
    private ArticlePresenter mArticlePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);

        ViewCompat.setTransitionName(mInnerContainer, TRANSITION_SHARED_ELEMENT);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);

        mArticlePresenter = new ArticlePresenterImpl(this);
        mArticlePresenter.loadArticle(getIntent().getExtras());
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
    public void showArticle(@NonNull Article article) {
        mTitleView.setText(article.getTitle());
        mTweetView.setText(article.getTweetCount());
        mProgressBar.setVisibility(View.VISIBLE);
        mWebView.loadUrl(article.getUrl());
    }
}
