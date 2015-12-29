package com.renovavision.cleanmvp.interactors;

import android.support.annotation.NonNull;
import android.util.Log;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.entities.Article;
import com.renovavision.cleanmvp.repositories.TweetRepository;
import com.renovavision.cleanmvp.repositories.TweetRepositoryImpl;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.UrlEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class TopArticlesInteractorImpl implements TopArticlesInteractor {

    @NonNull
    private final TweetRepository mTweetRepository;

    public TopArticlesInteractorImpl(@NonNull Injectable injectable) {
        this.mTweetRepository = new TweetRepositoryImpl(injectable);
    }

    @Override
    public void getTopArticles(@NonNull final Callback<List<Article>> callback) {
        mTweetRepository.getTimeline(
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        final List<Article> items = processTweets(result);
                        callback.success(items, result.response);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Log.d("API error", e.getMessage());
                        callback.failure(e);
                    }
                });
    }

    @NonNull
    private List<Article> processTweets(Result<List<Tweet>> result) {
        final List<Article> items = new ArrayList<>();
        for (Tweet tweet : result.data) {
            if (tweet.entities != null && tweet.entities.urls != null &&
                    tweet.entities.urls.size() > 0 &&
                    isEligibleDomain(tweet.entities.urls)) {
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
        final String title = tweet.text.split("http")[0];
        return new Article(title, tweet.entities.urls.get(0).expandedUrl, tweet.retweetCount,
                imgUrl == null ? "" : imgUrl + ":thumb");
    }

    private boolean isEligibleDomain(List<UrlEntity> urls) {
        if (urls == null || urls.get(0) == null || urls.get(0).expandedUrl == null) {
            return false;
        }
        final String url = urls.get(0).expandedUrl;
        return !url.contains("twitter.com") && !url.contains("goo.gl") &&
                !url.contains("vine.co") && !url.contains("vimeo.com") &&
                !url.contains("youtube.com") && !url.contains("youtu.be");
    }
}
