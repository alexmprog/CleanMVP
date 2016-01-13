package com.renovavision.cleanmvp.presenters;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by alexmprog on 13.01.2016.
 */
public interface ArticlePresenter extends BasePresenter {

    void loadArticle(@Nullable Bundle bundle);
}
