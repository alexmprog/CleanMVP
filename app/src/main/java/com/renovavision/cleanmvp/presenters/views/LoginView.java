package com.renovavision.cleanmvp.presenters.views;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface LoginView extends MvpView {

    void showProgress();

    void hideProgress();

    void showError(String errorMessage);

    void openTopArticlesView();
}
