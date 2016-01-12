package com.renovavision.cleanmvp.util.flow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.ui.activities.LoginActivity;
import com.renovavision.cleanmvp.ui.activities.HomeActivity;
import com.renovavision.cleanmvp.ui.activities.TopImagesActivity;

/**
 * Created by alexmprog on 04.01.2016.
 */
public class FlowManager {

    public void openTopArticlesScreen(@NonNull Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void openTopImagesScreen(@NonNull Context context) {
        Intent intent = new Intent(context, TopImagesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void openLoginScreen(@NonNull Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
