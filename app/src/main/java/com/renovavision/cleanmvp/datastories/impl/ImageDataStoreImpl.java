package com.renovavision.cleanmvp.datastories.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.datastories.DataStoreCallback;
import com.renovavision.cleanmvp.datastories.ImageDataStore;
import com.renovavision.cleanmvp.model.Image;

import java.util.List;

/**
 * Created by alexmprog on 13.01.2016.
 */
public class ImageDataStoreImpl implements ImageDataStore {

    @Override
    public void loadImages(@NonNull Context context, @NonNull DataStoreCallback<List<Image>> callback) {

    }
}
