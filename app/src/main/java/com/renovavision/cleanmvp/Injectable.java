package com.renovavision.cleanmvp;

import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.di.component.AppComponent;
import com.renovavision.cleanmvp.di.component.TwitterComponent;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface Injectable {

    @NonNull
    AppComponent getAppComponent();

    @NonNull
    TwitterComponent getTwitterComponent();

    void createTwitterComponent(@NonNull TwitterSession twitterSession);
}
