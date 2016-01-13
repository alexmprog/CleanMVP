package com.renovavision.cleanmvp.ui.views;

import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.model.Article;

/**
 * Created by alexmprog on 13.01.2016.
 */
public interface ArticleView extends BaseView {

    String EXTRA_ARTICLE = "article";
    String TRANSITION_SHARED_ELEMENT = "title";

    void showArticle(@NonNull Article article);
}
