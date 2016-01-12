package com.renovavision.cleanmvp.ui.views;

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
}
