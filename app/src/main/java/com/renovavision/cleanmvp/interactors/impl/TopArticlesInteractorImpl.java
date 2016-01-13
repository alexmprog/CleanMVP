package com.renovavision.cleanmvp.interactors.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.datastories.ArticleDataStore;
import com.renovavision.cleanmvp.datastories.DataStoreCallback;
import com.renovavision.cleanmvp.datastories.DataStoreException;
import com.renovavision.cleanmvp.datastories.impl.ArticleDataStoreImpl;
import com.renovavision.cleanmvp.interactors.TopArticlesInteractor;
import com.renovavision.cleanmvp.model.Article;
import com.renovavision.cleanmvp.repositories.TweetRepository;
import com.renovavision.cleanmvp.repositories.impl.TweetRepositoryImpl;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class TopArticlesInteractorImpl implements TopArticlesInteractor {

    @NonNull
    private TweetRepository mTweetRepository;

    private ArticleDataStore mArticleDataStore;

    public TopArticlesInteractorImpl(@NonNull Injectable injectable) {
        mTweetRepository = new TweetRepositoryImpl(injectable);
        mArticleDataStore = new ArticleDataStoreImpl(injectable);
    }

    @Override
    public void getTopArticles(@NonNull final Callback<List<Article>> callback) {
        mArticleDataStore.getArticles(new DataStoreCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> result) {
                if (result == null || result.isEmpty()) {
                    mTweetRepository.getTimeline(
                            new Callback<List<Tweet>>() {
                                @Override
                                public void success(Result<List<Tweet>> result) {
                                    final List<Article> items = parseTweets(result);
                                    mArticleDataStore.setArticles(items);
                                    callback.success(items, result.response);
                                }

                                @Override
                                public void failure(TwitterException e) {
                                    Log.d("API error", e.getMessage());
                                    callback.failure(e);
                                }
                            });
                } else {
                    callback.success(result, null);
                }
            }

            @Override
            public void onError(DataStoreException exception) {
                mTweetRepository.getTimeline(
                        new Callback<List<Tweet>>() {
                            @Override
                            public void success(Result<List<Tweet>> result) {
                                final List<Article> items = parseTweets(result);
                                mArticleDataStore.setArticles(items);
                                callback.success(items, result.response);
                            }

                            @Override
                            public void failure(TwitterException e) {
                                Log.d("API error", e.getMessage());
                                callback.failure(e);
                            }
                        });
            }
        });
    }

    @NonNull
    private List<Article> parseTweets(Result<List<Tweet>> result) {
        final List<Article> items = new ArrayList<>();
        for (Tweet tweet : result.data) {
            if (tweet.entities != null && tweet.entities.urls != null &&
                    !tweet.entities.urls.isEmpty()) {
                items.add(createArticle(tweet));
            }
        }
        Collections.sort(items);
        return items;
    }

    private Article createArticle(Tweet tweet) {
        String imgUrl = null;
        if (tweet.entities.media != null && tweet.entities.media.size() > 0) {
            imgUrl = tweet.entities.media.get(0).mediaUrl;
        }
        final String title = tweet.text;
        return new Article(title, tweet.entities.urls.get(0).expandedUrl, tweet.retweetCount,
                imgUrl == null ? "" : imgUrl + ":thumb");
    }
}
