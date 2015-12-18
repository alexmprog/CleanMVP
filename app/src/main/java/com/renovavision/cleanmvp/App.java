package com.renovavision.cleanmvp;

import android.app.Application;

import com.renovavision.cleanmvp.di.component.AppComponent;
import com.renovavision.cleanmvp.di.component.DaggerAppComponent;
import com.renovavision.cleanmvp.di.component.TwitterComponent;
import com.renovavision.cleanmvp.di.module.AppModule;
import com.renovavision.cleanmvp.util.config.BuildConfigManager;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

/**
 * Created by alexmprog on 18.12.2015.
 */
public class App extends Application {

    private AppComponent mAppComponent;
    private TwitterComponent mTwitterComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        buildObjectGraph();
        setUpFabric();
    }

    private void buildObjectGraph() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void setUpFabric() {
        BuildConfigManager configManager = getAppComponent().getBuildConfigManager();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(configManager.getTwitterConsumerKey(), configManager.getTwitterConsumerSecret());
        Fabric.with(this, new TwitterCore(authConfig));
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public TwitterComponent getTwitterComponent() {
        return mTwitterComponent;
    }
}
