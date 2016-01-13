package com.renovavision.cleanmvp.datastories;

import android.support.annotation.NonNull;

/**
 * Created by alexmprog on 13.01.2016.
 */
public class DataStoreException extends Exception {

    public DataStoreException(@NonNull String message, @NonNull Throwable throwable) {
        super(message, throwable);
    }

}
