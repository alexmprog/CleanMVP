package com.renovavision.cleanmvp.presenters.impl;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.presenters.SplashPresenter;
import com.renovavision.cleanmvp.ui.views.BaseView;
import com.renovavision.cleanmvp.ui.views.SplashView;
import com.renovavision.cleanmvp.util.screen.ScreenManager;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by alexmprog on 12.01.2016.
 */
public class SplashPresenterImpl extends BasePresenterImpl implements SplashPresenter {

    private static final long REDIRECT_DELAY = 1500;

    @NonNull
    private Handler mHandler;

    @NonNull
    private WeakReference<SplashView> mSplashViewRef;

    @NonNull
    Injectable mInjectable;

    @Inject
    ScreenManager mScreenManager;

    public SplashPresenterImpl(@NonNull SplashView splashView, @NonNull Injectable injectable) {
        mSplashViewRef = new WeakReference<>(splashView);
        mHandler = new Handler();
        mInjectable = injectable;
        injectable.getScreenComponent().inject(this);
    }

    @Override
    public void onViewCreate() {
        mHandler.postDelayed(new RedirectRunnable(this), REDIRECT_DELAY);
    }

    @Override
    public void onViewDestroy() {
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public SplashView getView() {
        return mSplashViewRef.get();
    }

    private static final class RedirectRunnable implements Runnable {

        private WeakReference<SplashPresenterImpl> mSplashPresenter;

        private RedirectRunnable(@NonNull SplashPresenterImpl splashPresenter) {
            this.mSplashPresenter = new WeakReference<>(splashPresenter);
        }

        @Override
        public void run() {
            SplashPresenterImpl presenter = mSplashPresenter.get();
            if (presenter == null) {
                return;
            }

            BaseView view = presenter.getView();
            if (view == null) {
                return;
            }

            Context context = view.getContext();
            ScreenManager flowManager = presenter.mScreenManager;
            TwitterSession activeSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
            if (activeSession == null) {
                flowManager.openLoginScreen(context);
            } else {
                presenter.mInjectable.createTwitterComponent(activeSession);
                flowManager.openHomeScreen(context);
            }
            view.finishView();
        }
    }
}
