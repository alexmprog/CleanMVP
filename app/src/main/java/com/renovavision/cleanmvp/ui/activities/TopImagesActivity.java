package com.renovavision.cleanmvp.ui.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.model.Image;
import com.renovavision.cleanmvp.ui.views.TopImagesView;

import java.util.List;

/**
 * Created by alexmprog on 04.01.2016.
 */
public class TopImagesActivity extends AppCompatActivity implements TopImagesView {


    @Override
    public void showImages(List<Image> imageList) {

    }

    @NonNull
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finishView() {
        finish();
    }
}
