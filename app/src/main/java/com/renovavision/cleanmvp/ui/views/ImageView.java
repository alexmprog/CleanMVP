package com.renovavision.cleanmvp.ui.views;

import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.model.Image;

/**
 * Created by alexmprog on 13.01.2016.
 */
public interface ImageView extends BaseView {

    String EXTRA_IMAGE = "image";
    String TRANSITION_SHARED_ELEMENT = "content";

    void showImage(@NonNull Image image);

}
