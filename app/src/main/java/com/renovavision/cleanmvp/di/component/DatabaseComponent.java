package com.renovavision.cleanmvp.di.component;

import com.renovavision.cleanmvp.datastories.impl.ArticleDataStoreImpl;
import com.renovavision.cleanmvp.datastories.impl.ImageDataStoreImpl;
import com.renovavision.cleanmvp.datastories.impl.ProfileDataStoreImpl;
import com.renovavision.cleanmvp.di.module.DatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexmprog on 13.01.2016.
 */

@Singleton
@Component(modules = DatabaseModule.class)
public interface DatabaseComponent {

    void inject(ArticleDataStoreImpl articleDataStore);

    void inject(ImageDataStoreImpl imageDataStore);

    void inject(ProfileDataStoreImpl profileDataStore);
}
