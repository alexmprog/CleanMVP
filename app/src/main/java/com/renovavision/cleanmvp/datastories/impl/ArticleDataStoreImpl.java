package com.renovavision.cleanmvp.datastories.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.database.DatabaseManager;
import com.renovavision.cleanmvp.datastories.ArticleDataStore;
import com.renovavision.cleanmvp.datastories.DataStoreCallback;
import com.renovavision.cleanmvp.datastories.DataStoreException;
import com.renovavision.cleanmvp.model.Article;
import com.snappydb.SnappydbException;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by alexmprog on 13.01.2016.
 */
public class ArticleDataStoreImpl implements ArticleDataStore {

    private static final String TAG = ArticleDataStoreImpl.class.getSimpleName();

    @Inject
    DatabaseManager mDatabaseManager;

    public ArticleDataStoreImpl(@NonNull Injectable injectable) {
        injectable.getDatabaseComponent().inject(this);
    }

    @Override
    public void getArticles(@NonNull DataStoreCallback<List<Article>> callback) {
        try {
            List<Article> articles = mDatabaseManager.getArticles();
            callback.onSuccess(articles);
        } catch (SnappydbException e) {
            Log.e(TAG, "Can not load articles", e);
            callback.onError(new DataStoreException("Can not load articles", e));
        }
    }

    @Override
    public void setArticles(@NonNull List<Article> articleList) {
        try {
            mDatabaseManager.setArticles(articleList);
        } catch (SnappydbException e) {
            Log.e(TAG, "Can not insert articles", e);
        }
    }
}
