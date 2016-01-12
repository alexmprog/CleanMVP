package com.renovavision.cleanmvp.interactors;

import com.renovavision.cleanmvp.model.Image;
import com.twitter.sdk.android.core.Callback;

import java.util.List;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface TopImagesInteractor {
    void getTopImages(Callback<List<Image>> callback);
}
