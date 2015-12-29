package com.renovavision.cleanmvp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.presenters.SplashPresenter;
import com.renovavision.cleanmvp.presenters.impl.SplashPresenterImpl;
import com.renovavision.cleanmvp.presenters.views.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @NonNull
    private SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashPresenter = new SplashPresenterImpl(this);
        mSplashPresenter.onViewCreated();
    }

    @Override
    public void openLoginView() {
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void openTopArticlesView() {
        finish();
        startActivity(new Intent(this, TopArticlesActivity.class));
    }

    @NonNull
    @Override
    public Context getContext() {
        return this;
    }

    @NonNull
    @Override
    public Injectable getInjectable() {
        return (Injectable) getApplication();
    }
}
