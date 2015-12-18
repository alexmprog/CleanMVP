package com.renovavision.cleanmvp.api;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by alexmprog on 18.12.2015.
 */
public class CustomApiClient extends TwitterApiClient {

    private final TwitterSession session;

    public CustomApiClient(TwitterSession session) {
        super(session);
        this.session = session;
    }

    public TimelineService getTimelineService() {
        return this.getService(TimelineService.class);
    }

    public UserService getUserService() {
        return this.getService(UserService.class);
    }

    public void userShow(Callback<User> callback) {
        getUserService().userShow(session.getUserName(), true, callback);
    }
}
