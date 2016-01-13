package com.renovavision.cleanmvp.interactors;

import com.renovavision.cleanmvp.model.Article;
import com.twitter.sdk.android.core.Callback;

import java.util.List;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface TopArticlesInteractor {

    void getTopArticles(Callback<List<Article>> callback);
}
