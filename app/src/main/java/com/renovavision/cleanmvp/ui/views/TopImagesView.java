package com.renovavision.cleanmvp.ui.views;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.renovavision.cleanmvp.model.Image;

import java.util.List;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface TopImagesView extends BaseView {

    void showImages(List<Image> imageList);

    void showMessage(String message);

    void showProgress();

    void hideProgress();

    @NonNull
    Activity getActivity();

    @NonNull
    View getTransitionAnimationView(View view);
}
