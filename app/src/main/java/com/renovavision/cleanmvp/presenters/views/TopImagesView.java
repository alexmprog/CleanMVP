package com.renovavision.cleanmvp.presenters.views;

import com.renovavision.cleanmvp.entities.Image;

import java.util.List;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface TopImagesView {
    void showImages(List<Image> imageList);
}
