package com.renovavision.cleanmvp.ui.views;

import android.content.Context;
import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.Injectable;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface BaseView {

    @NonNull
    Context getContext();

    void finishView();
}
