package com.renovavision.cleanmvp.ui.views;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface LoginView extends BaseView {

    void showProgress();

    void hideProgress();

    void showMessage(String message);
}
