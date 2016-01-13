package com.renovavision.cleanmvp.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.database.DatabaseManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexmprog on 13.01.2016.
 */

@Module
public class DatabaseModule {

    @NonNull
    Context mApplicationContext;

    public DatabaseModule(@NonNull Context applicationContext) {
        mApplicationContext = applicationContext;
    }

    @Provides
    @Singleton
    DatabaseManager providesDatabaseManager() {
        return new DatabaseManager(mApplicationContext);
    }
}
