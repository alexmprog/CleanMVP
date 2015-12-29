package com.renovavision.cleanmvp.presenters.views;

import com.renovavision.cleanmvp.entities.Article;

import java.util.List;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface TopArticlesView {

    void showArticles(List<Article> articlesList);
}
