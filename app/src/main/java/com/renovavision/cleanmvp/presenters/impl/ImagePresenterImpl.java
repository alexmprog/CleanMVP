package com.renovavision.cleanmvp.presenters.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.renovavision.cleanmvp.model.Image;
import com.renovavision.cleanmvp.presenters.ImagePresenter;
import com.renovavision.cleanmvp.ui.views.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by alexmprog on 13.01.2016.
 */
public class ImagePresenterImpl extends BasePresenterImpl implements ImagePresenter {

    @NonNull
    private WeakReference<ImageView> mImageViewRef;

    public ImagePresenterImpl(@NonNull ImageView imageView) {
        mImageViewRef = new WeakReference<>(imageView);
    }

    @Override
    public void loadImage(@Nullable Bundle bundle) {
        ImageView view = getView();
        if (view == null) {
            return;
        }

        if (bundle == null) {
            view.finishView();
            return;
        }

        Image image = bundle.getParcelable(ImageView.EXTRA_IMAGE);
        if (image == null) {
            view.finishView();
            return;
        }

        view.showImage(image);
    }

    @Override
    public ImageView getView() {
        return mImageViewRef.get();
    }
}
