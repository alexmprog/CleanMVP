package com.renovavision.cleanmvp.presenters.impl;

import com.renovavision.cleanmvp.presenters.BasePresenter;
import com.renovavision.cleanmvp.ui.views.BaseView;

/**
 * Created by alexmprog on 12.01.2016.
 */
public abstract class BasePresenterImpl implements BasePresenter {

    @Override
    public void onViewCreate() {
        // do nothing - will change this behavior in child class
    }

    @Override
    public void onViewResume() {
        // do nothing - will change this behavior in child class
    }

    @Override
    public void onViewPause() {
        // do nothing - will change this behavior in child class
    }

    @Override
    public void onViewDestroy() {
        // do nothing - will change this behavior in child class
    }

    @Override
    public abstract BaseView getView();
}
