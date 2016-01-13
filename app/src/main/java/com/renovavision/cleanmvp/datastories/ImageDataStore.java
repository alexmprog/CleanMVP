package com.renovavision.cleanmvp.datastories;

import android.content.Context;
import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.model.Image;

import java.util.List;

/**
 * Created by alexmprog on 13.01.2016.
 */
public interface ImageDataStore {

    void loadImages(@NonNull Context context, @NonNull DataStoreCallback<List<Image>> callback);
}
