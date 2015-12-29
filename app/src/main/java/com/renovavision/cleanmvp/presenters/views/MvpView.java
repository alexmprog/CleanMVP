package com.renovavision.cleanmvp.presenters.views;

import android.content.Context;
import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.Injectable;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface MvpView {

    @NonNull
    Context getContext();

    @NonNull
    Injectable getInjectable();
}
