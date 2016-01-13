package com.renovavision.cleanmvp.presenters.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.renovavision.cleanmvp.model.Article;
import com.renovavision.cleanmvp.presenters.ArticlePresenter;
import com.renovavision.cleanmvp.ui.views.ArticleView;

import java.lang.ref.WeakReference;

/**
 * Created by alexmprog on 13.01.2016.
 */
public class ArticlePresenterImpl extends BasePresenterImpl implements ArticlePresenter {

    @NonNull
    private WeakReference<ArticleView> mArticleViewRef;

    public ArticlePresenterImpl(@NonNull ArticleView articleView) {
        mArticleViewRef = new WeakReference<>(articleView);
    }

    @Override
    public void loadArticle(@Nullable Bundle bundle) {
        ArticleView view = getView();
        if (view == null) {
            return;
        }

        if (bundle == null) {
            view.finishView();
            return;
        }

        Article article = bundle.getParcelable(ArticleView.EXTRA_ARTICLE);
        if (article == null) {
            view.finishView();
            return;
        }

        view.showArticle(article);
    }

    @Override
    public ArticleView getView() {
        return mArticleViewRef.get();
    }
}
