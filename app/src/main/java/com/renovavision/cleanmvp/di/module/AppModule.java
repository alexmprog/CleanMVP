package com.renovavision.cleanmvp.di.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.renovavision.cleanmvp.App;
import com.renovavision.cleanmvp.util.config.BuildConfigManager;
import com.renovavision.cleanmvp.util.holder.PresenterHolder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexmprog on 18.12.2015.
 */

@Module
public class AppModule {

    App mApplication;

    public AppModule(App application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    App providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(App application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    BuildConfigManager providesBuildConfigManager() {
        return new BuildConfigManager();
    }

    @Provides
    @Singleton
    PresenterHolder providesPresenterHolder() {
        return new PresenterHolder();
    }
}
