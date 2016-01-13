package com.renovavision.cleanmvp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.presenters.LoginPresenter;
import com.renovavision.cleanmvp.presenters.impl.LoginPresenterImpl;
import com.renovavision.cleanmvp.ui.views.LoginView;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.twitter_login_button)
    TwitterLoginButton loginButton;

    @Bind(R.id.login_container)
    FrameLayout loginContainer;

    @NonNull
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginPresenter = new LoginPresenterImpl(this, (Injectable) getApplication());
        mLoginPresenter.configureLoginButton(loginButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showMessage(String mMessage) {
        Snackbar.make(loginContainer, R.string.try_again, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finishView() {
        finish();
    }
}
