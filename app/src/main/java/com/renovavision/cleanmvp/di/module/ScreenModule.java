package com.renovavision.cleanmvp.di.module;

import com.renovavision.cleanmvp.util.screen.ScreenManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexmprog on 13.01.2016.
 */

@Module
public class ScreenModule {

    @Provides
    @Singleton
    ScreenManager providesScreenManager() {
        return new ScreenManager();
    }
}
