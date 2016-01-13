package com.renovavision.cleanmvp.ui.views;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.renovavision.cleanmvp.model.Article;

import java.util.List;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface TopArticlesView extends BaseView {

    void showArticles(List<Article> articlesList);

    void showMessage(String message);

    void showProgress();

    void hideProgress();

    @NonNull
    Activity getActivity();

    @NonNull
    View getTransitionAnimationView(View view);
}
