package com.renovavision.cleanmvp.datastories.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.datastories.DataStoreCallback;
import com.renovavision.cleanmvp.datastories.ProfileDataStore;
import com.renovavision.cleanmvp.model.Profile;

import java.util.List;

/**
 * Created by alexmprog on 13.01.2016.
 */
public class ProfileDataStoreImpl implements ProfileDataStore {

    @Override
    public void loadProfile(@NonNull Context context, @NonNull DataStoreCallback<List<Profile>> callback) {

    }
}
