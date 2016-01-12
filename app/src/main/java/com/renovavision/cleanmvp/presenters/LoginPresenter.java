package com.renovavision.cleanmvp.presenters;

import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface LoginPresenter extends BasePresenter {

    void configureLoginButton(TwitterLoginButton loginButton);
}
