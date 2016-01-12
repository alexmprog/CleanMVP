package com.renovavision.cleanmvp.ui.views;

import com.renovavision.cleanmvp.model.Image;

import java.util.List;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface TopImagesView extends BaseView {
    void showImages(List<Image> imageList);
}
