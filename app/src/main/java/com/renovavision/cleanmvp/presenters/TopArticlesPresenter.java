package com.renovavision.cleanmvp.presenters;

import android.view.View;

/**
 * Created by alexmprog on 12.01.2016.
 */
public interface TopArticlesPresenter extends BasePresenter {

    void openArticle(View view, int position);
}
