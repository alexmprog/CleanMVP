package com.renovavision.cleanmvp.presenters.impl;

import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.presenters.LoginPresenter;
import com.renovavision.cleanmvp.presenters.views.LoginView;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.lang.ref.WeakReference;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class LoginPresenterImpl implements LoginPresenter {

    @NonNull
    private WeakReference<LoginView> mLoginViewRef;

    public LoginPresenterImpl(@NonNull LoginView loginView) {
        this.mLoginViewRef = new WeakReference<>(loginView);
    }

    @Override
    public void configureLoginButton(final TwitterLoginButton loginButton) {
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                LoginView loginView = mLoginViewRef.get();
                if (loginView == null) {
                    return;
                }

                Injectable injectable = loginView.getInjectable();
                injectable.createTwitterComponent(result.data);
                loginView.openTopArticlesView();
            }

            @Override
            public void failure(TwitterException exception) {
                LoginView loginView = mLoginViewRef.get();
                if (loginView == null) {
                    return;
                }

                loginView.showError(exception.getMessage());
            }
        });
    }
}
