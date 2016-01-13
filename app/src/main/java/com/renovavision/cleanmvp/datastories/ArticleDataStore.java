package com.renovavision.cleanmvp.datastories;

import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.model.Article;

import java.util.List;

/**
 * Created by alexmprog on 13.01.2016.
 */
public interface ArticleDataStore {
    void getArticles(@NonNull DataStoreCallback<List<Article>> callback);

    void setArticles(@NonNull List<Article> articleList);
}
