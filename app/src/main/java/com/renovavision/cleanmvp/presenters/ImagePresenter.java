package com.renovavision.cleanmvp.presenters;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by alexmprog on 13.01.2016.
 */
public interface ImagePresenter extends BasePresenter {

    void loadImage(@Nullable Bundle bundle);
}
