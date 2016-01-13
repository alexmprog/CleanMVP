package com.renovavision.cleanmvp.presenters;

import android.support.annotation.NonNull;

import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface LoginPresenter extends BasePresenter {

    void configureLoginButton(@NonNull TwitterLoginButton loginButton);
}
