package com.renovavision.cleanmvp.datastories;

/**
 * Created by alexmprog on 13.01.2016.
 */
public interface DataStoreCallback<T> {

    void onSuccess(T result);

    void onError(DataStoreException exception);
}
