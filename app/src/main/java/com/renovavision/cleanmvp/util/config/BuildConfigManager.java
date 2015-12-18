package com.renovavision.cleanmvp.util.config;

import com.renovavision.cleanmvp.BuildConfig;

/**
 * Created by alexmprog on 18.12.2015.
 */
public class BuildConfigManager {

    public BuildConfigManager() {

    }

    public String getTwitterConsumerKey() {
        return BuildConfig.CONSUMER_KEY;
    }

    public String getTwitterConsumerSecret() {
        return BuildConfig.CONSUMER_SECRET;
    }
}
