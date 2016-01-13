package com.renovavision.cleanmvp.di.component;

import com.renovavision.cleanmvp.App;
import com.renovavision.cleanmvp.di.module.AppModule;
import com.renovavision.cleanmvp.util.config.BuildConfigManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexmprog on 18.12.2015.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    BuildConfigManager getBuildConfigManager();

    App getApplication();

}
