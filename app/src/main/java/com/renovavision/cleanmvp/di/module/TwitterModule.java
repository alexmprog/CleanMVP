package com.renovavision.cleanmvp.di.module;

import com.renovavision.cleanmvp.api.CustomTwitterApi;
import com.renovavision.cleanmvp.di.scope.UserScope;
import com.twitter.sdk.android.core.TwitterSession;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexmprog on 18.12.2015.
 */

@Module
public class TwitterModule {

    TwitterSession mSession;

    public TwitterModule(TwitterSession session) {
        this.mSession = session;
    }

    @UserScope
    @Provides
    TwitterSession providesTwitterSession() {
        return mSession;
    }

    @UserScope
    @Provides
    CustomTwitterApi providesTwitterApi(TwitterSession twitterSession) {
        return new CustomTwitterApi(twitterSession);
    }
}
