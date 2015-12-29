package com.renovavision.cleanmvp.repositories;

import android.support.annotation.NonNull;
import android.util.Log;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.api.CustomTwitterApi;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class TweetRepositoryImpl implements TweetRepository {

    @Inject
    CustomTwitterApi mTwitterApi;

    public TweetRepositoryImpl(@NonNull Injectable injectable) {
        injectable.getTwitterComponent().inject(this);
    }

    @Override
    public void getTimeline(@NonNull final Callback<List<Tweet>> callback) {
        mTwitterApi.getTimelineService().getTimeline(200, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        callback.success(result.data, result.response);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Log.d("API error", e.getMessage());
                        callback.failure(e);
                    }
                });
    }
}
