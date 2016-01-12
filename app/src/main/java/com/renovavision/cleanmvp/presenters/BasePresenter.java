package com.renovavision.cleanmvp.presenters;

import com.renovavision.cleanmvp.ui.views.BaseView;

/**
 * Created by alexmprog on 18.12.2015.
 */
public interface BasePresenter {

    void onViewCreate();

    void onViewResume();

    void onViewPause();

    void onViewDestroy();

    BaseView getView();
}

