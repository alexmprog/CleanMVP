package com.renovavision.cleanmvp.api;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by alexmprog on 18.12.2015.
 */
public interface TwitterTimelineService {

    @GET("/1.1/statuses/home_timeline.json")
    void getTimeline(@Query("count") int count, @Query("trim_user") boolean trimUser, @Query
            ("exclude_replies") boolean excludeReplies, @Query("contributor_details") boolean contributor,
                     @Query("include_entities") boolean includeEntities, Callback<List<Tweet>> callback);

}
