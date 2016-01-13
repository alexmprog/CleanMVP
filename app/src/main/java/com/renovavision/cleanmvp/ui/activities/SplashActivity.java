package com.renovavision.cleanmvp.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.presenters.SplashPresenter;
import com.renovavision.cleanmvp.presenters.impl.SplashPresenterImpl;
import com.renovavision.cleanmvp.ui.views.SplashView;

/**
 * Created by alexmprog on 12.01.2016.
 */
public class SplashActivity extends AppCompatActivity implements SplashView {

    @NonNull
    private SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashPresenter = new SplashPresenterImpl(this, (Injectable) getApplication());
        mSplashPresenter.onViewCreate();
    }

    @NonNull
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finishView() {
        finish();
    }
}
