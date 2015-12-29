package com.renovavision.cleanmvp.api;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by alexmprog on 18.12.2015.
 */
public class CustomTwitterApi extends TwitterApiClient {

    private final TwitterSession session;

    public CustomTwitterApi(TwitterSession session) {
        super(session);
        this.session = session;
    }

    public TwitterTimelineService getTimelineService() {
        return this.getService(TwitterTimelineService.class);
    }

    public TwitterUserService getUserService() {
        return this.getService(TwitterUserService.class);
    }

    public void getUser(Callback<User> callback) {
        getUserService().getUser(session.getUserName(), true, callback);
    }
}
