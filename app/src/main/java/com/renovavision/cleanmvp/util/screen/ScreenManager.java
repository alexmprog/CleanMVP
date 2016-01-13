package com.renovavision.cleanmvp.util.screen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.renovavision.cleanmvp.model.Article;
import com.renovavision.cleanmvp.model.Image;
import com.renovavision.cleanmvp.ui.activities.ArticleActivity;
import com.renovavision.cleanmvp.ui.activities.ImageActivity;
import com.renovavision.cleanmvp.ui.activities.LoginActivity;
import com.renovavision.cleanmvp.ui.activities.HomeActivity;
import com.renovavision.cleanmvp.ui.activities.SplashActivity;
import com.renovavision.cleanmvp.ui.activities.TopImagesActivity;
import com.renovavision.cleanmvp.ui.views.ArticleView;
import com.renovavision.cleanmvp.ui.views.ImageView;

/**
 * Created by alexmprog on 04.01.2016.
 */
public class ScreenManager {

    public void openHomeScreen(@NonNull Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void openTopImagesScreen(@NonNull Context context) {
        Intent intent = new Intent(context, TopImagesActivity.class);
        context.startActivity(intent);
    }

    public void openLoginScreen(@NonNull Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void openSplashScreen(@NonNull Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void openArticleScreen(@NonNull Activity activity, @NonNull Article article, @NonNull View innerContainer) {
        Intent intent = new Intent(activity, ArticleActivity.class);
        intent.putExtra(ArticleView.EXTRA_ARTICLE, article);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, innerContainer, ArticleView.TRANSITION_SHARED_ELEMENT);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public void openImageScreen(@NonNull Activity activity, @NonNull Image image, @NonNull View innerContainer) {
        Intent intent = new Intent(activity, ImageActivity.class);
        intent.putExtra(ImageView.EXTRA_IMAGE, image);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, innerContainer, ImageView.TRANSITION_SHARED_ELEMENT);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
