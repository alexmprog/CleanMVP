package com.renovavision.cleanmvp.di.module;

import com.renovavision.cleanmvp.di.scope.ScreenScope;
import com.renovavision.cleanmvp.util.screen.ScreenManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexmprog on 13.01.2016.
 */

@Module
public class ScreenModule {

    @Provides
    @ScreenScope
    ScreenManager providesScreenManager() {
        return new ScreenManager();
    }
}
