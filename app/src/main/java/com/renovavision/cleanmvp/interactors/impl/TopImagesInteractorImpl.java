package com.renovavision.cleanmvp.interactors.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.interactors.TopImagesInteractor;
import com.renovavision.cleanmvp.model.Image;
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
public class TopImagesInteractorImpl implements TopImagesInteractor {

    @NonNull
    private final TweetRepository mTweetRepository;

    public TopImagesInteractorImpl(@NonNull Injectable injectable) {
        this.mTweetRepository = new TweetRepositoryImpl(injectable);
    }

    @Override
    public void getTopImages(@NonNull final Callback<List<Image>> callback) {
        mTweetRepository.getTimeline(
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        final List<Image> items = processTweets(result);
                        callback.success(items, null);

                    }

                    @Override
                    public void failure(TwitterException e) {
                        Log.d("API error", e.getMessage());
                        callback.failure(e);
                    }
                });
    }

    private List<Image> processTweets(Result<List<Tweet>> result) {
        final List<Image> items = new ArrayList<>();
        for (Tweet tweet : result.data) {
            if (tweet.entities != null && tweet.entities.media != null &&
                    tweet.entities.media.size() > 0) {
                items.add(createImage(tweet));
            }
        }
        Collections.sort(items);
        return items;
    }

    private Image createImage(Tweet tweet) {
        String imgUrl = null;
        if (tweet.entities.media != null && tweet.entities.media.size() > 0) {
            imgUrl = tweet.entities.media.get(0).mediaUrl;
        }
        final String title = tweet.text;
        return new Image(title, tweet.retweetCount,
                imgUrl == null ? "" : imgUrl + ":large");
    }
}
