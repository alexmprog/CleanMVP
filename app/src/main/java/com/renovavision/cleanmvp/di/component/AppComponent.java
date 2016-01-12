package com.renovavision.cleanmvp.di.component;

import android.content.SharedPreferences;

import com.renovavision.cleanmvp.App;
import com.renovavision.cleanmvp.di.module.AppModule;
import com.renovavision.cleanmvp.presenters.LoginPresenterImpl;
import com.renovavision.cleanmvp.presenters.SplashPresenterImpl;
import com.renovavision.cleanmvp.presenters.TopArticlesPresenterImpl;
import com.renovavision.cleanmvp.util.config.BuildConfigManager;
import com.renovavision.cleanmvp.util.flow.FlowManager;
import com.renovavision.cleanmvp.util.holder.PresenterHolder;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexmprog on 18.12.2015.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(LoginPresenterImpl loginPresenter);

    void inject(SplashPresenterImpl splashPresenter);

    void inject(TopArticlesPresenterImpl topArticlesPresenter);

    BuildConfigManager getBuildConfigManager();

    App getApplication();

    SharedPreferences getSharePreferences();

    PresenterHolder getPresenterHolder();

    FlowManager getFlowManager();

}
