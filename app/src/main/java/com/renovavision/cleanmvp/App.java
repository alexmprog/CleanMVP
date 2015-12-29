package com.renovavision.cleanmvp;

import android.app.Application;
import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.di.component.AppComponent;
import com.renovavision.cleanmvp.di.component.DaggerAppComponent;
import com.renovavision.cleanmvp.di.component.DaggerTwitterComponent;
import com.renovavision.cleanmvp.di.component.TwitterComponent;
import com.renovavision.cleanmvp.di.module.AppModule;
import com.renovavision.cleanmvp.di.module.TwitterModule;
import com.renovavision.cleanmvp.util.config.BuildConfigManager;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import io.fabric.sdk.android.Fabric;

/**
 * Created by alexmprog on 18.12.2015.
 */
public class App extends Application implements Injectable {

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

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @NonNull
    public TwitterComponent getTwitterComponent() {
        return mTwitterComponent;
    }

    @Override
    public void createTwitterComponent(@NonNull TwitterSession twitterSession) {
        mTwitterComponent = DaggerTwitterComponent.builder().appComponent(mAppComponent).twitterModule(new TwitterModule(twitterSession)).build();
    }
}
