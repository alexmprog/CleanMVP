package com.renovavision.cleanmvp.presenters;

import android.view.View;

/**
 * Created by alexmprog on 13.01.2016.
 */
public interface TopImagesPresenter extends BasePresenter {

    void openImage(View view, int position);
}
