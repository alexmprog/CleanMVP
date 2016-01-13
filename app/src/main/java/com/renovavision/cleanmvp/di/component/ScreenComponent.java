package com.renovavision.cleanmvp.di.component;

import com.renovavision.cleanmvp.di.module.ScreenModule;
import com.renovavision.cleanmvp.di.scope.ScreenScope;
import com.renovavision.cleanmvp.presenters.impl.LoginPresenterImpl;
import com.renovavision.cleanmvp.presenters.impl.ProfilePresenterImpl;
import com.renovavision.cleanmvp.presenters.impl.SplashPresenterImpl;
import com.renovavision.cleanmvp.presenters.impl.TopArticlesPresenterImpl;
import com.renovavision.cleanmvp.presenters.impl.TopImagesPresenterImpl;

import dagger.Component;

/**
 * Created by alexmprog on 13.01.2016.
 */

@ScreenScope
@Component(modules = ScreenModule.class)
public interface ScreenComponent {

    void inject(LoginPresenterImpl loginPresenter);

    void inject(SplashPresenterImpl splashPresenter);

    void inject(TopArticlesPresenterImpl topArticlesPresenter);

    void inject(TopImagesPresenterImpl topArticlesPresenter);

    void inject(ProfilePresenterImpl profilePresenter);
}
