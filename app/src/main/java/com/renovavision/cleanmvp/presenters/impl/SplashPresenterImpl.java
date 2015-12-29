package com.renovavision.cleanmvp.presenters.impl;

import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.presenters.SplashPresenter;
import com.renovavision.cleanmvp.presenters.views.SplashView;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.lang.ref.WeakReference;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class SplashPresenterImpl implements SplashPresenter {

    @NonNull
    private WeakReference<SplashView> mSplashViewRef;

    public SplashPresenterImpl(@NonNull SplashView mSplashView) {
        this.mSplashViewRef = new WeakReference<>(mSplashView);
    }

    @Override
    public void onViewCreated() {
        SplashView splashView = mSplashViewRef.get();
        if (splashView == null) {
            return;
        }

        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession == null) {
            Injectable injectable = splashView.getInjectable();
            injectable.createTwitterComponent(twitterSession);
            splashView.openLoginView();
        } else {
            splashView.openTopArticlesView();
        }
    }
}
