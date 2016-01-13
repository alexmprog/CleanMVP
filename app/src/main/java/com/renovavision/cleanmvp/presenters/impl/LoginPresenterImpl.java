package com.renovavision.cleanmvp.presenters.impl;

import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.presenters.LoginPresenter;
import com.renovavision.cleanmvp.ui.views.LoginView;
import com.renovavision.cleanmvp.util.screen.ScreenManager;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class LoginPresenterImpl extends BasePresenterImpl implements LoginPresenter {

    @Inject
    ScreenManager mScreenManager;

    @NonNull
    private WeakReference<LoginView> mLoginViewRef;

    @NonNull
    private Injectable mInjectable;

    public LoginPresenterImpl(@NonNull LoginView loginView, @NonNull Injectable injectable) {
        mLoginViewRef = new WeakReference<>(loginView);
        mInjectable = injectable;
        injectable.getScreenComponent().inject(this);
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

                // create twitter service
                mInjectable.createTwitterComponent(result.data);

                // open top articles screen
                mScreenManager.openHomeScreen(loginView.getContext());
                loginView.finishView();
            }

            @Override
            public void failure(TwitterException exception) {
                LoginView loginView = mLoginViewRef.get();
                if (loginView == null) {
                    return;
                }

                loginView.showMessage(exception.getMessage());
            }
        });
    }

    @Override
    public LoginView getView() {
        return mLoginViewRef.get();
    }
}
