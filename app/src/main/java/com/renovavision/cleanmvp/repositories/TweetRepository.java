package com.renovavision.cleanmvp.repositories;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by alexmprog on 18.12.2015.
 */
public interface TweetRepository {

    void getTimeline(final Callback<List<Tweet>> callback);
}
