package com.renovavision.cleanmvp.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.renovavision.cleanmvp.model.Article;
import com.renovavision.cleanmvp.model.Image;
import com.renovavision.cleanmvp.model.Profile;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alexmprog on 13.01.2016.
 */
public class DatabaseManager {

    private static final String TAG = DatabaseManager.class.getName();

    // database name
    private static final String DB_NAME = "CleanMvp";

    // images key
    private static final String IMAGE_KEY = "image_key";

    // articles key
    private static final String ARTICLE_KEY = "article_key";

    // profile key
    private static final String PROFILE_KEY = "profile_key";

    private DB mSnappyDb;

    public DatabaseManager(@NonNull Context context) {
        try {
            mSnappyDb = openDatabase(context);
        } catch (SnappydbException e) {
            Log.d(TAG, "Can not open database", e);
        }
    }

    private DB openDatabase(@NonNull Context context) throws SnappydbException {
        return DBFactory.open(context, DB_NAME);
    }

    public List<Image> getImages() throws SnappydbException {
        return Arrays.asList(mSnappyDb.getObjectArray(IMAGE_KEY, Image.class));
    }

    public void setImages(List<Image> images) throws SnappydbException {
        mSnappyDb.put(IMAGE_KEY, images);
    }

    public List<Article> getArticles() throws SnappydbException {
        return Arrays.asList(mSnappyDb.getObjectArray(ARTICLE_KEY, Article.class));
    }

    public void setArticles(List<Article> articles) throws SnappydbException {
        mSnappyDb.put(ARTICLE_KEY, articles);
    }

    public Profile getProfile() throws SnappydbException {
        return mSnappyDb.getObject(PROFILE_KEY, Profile.class);
    }

    public void setProfile(Profile profile) throws SnappydbException {
        mSnappyDb.put(PROFILE_KEY, profile);
    }
}
