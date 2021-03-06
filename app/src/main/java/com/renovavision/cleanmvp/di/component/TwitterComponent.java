package com.renovavision.cleanmvp.di.component;

import com.renovavision.cleanmvp.di.module.TwitterModule;
import com.renovavision.cleanmvp.di.scope.UserScope;
import com.renovavision.cleanmvp.repositories.impl.TweetRepositoryImpl;
import com.renovavision.cleanmvp.repositories.impl.UserRepositoryImpl;

import dagger.Component;

/**
 * Created by alexmprog on 18.12.2015.
 */

@UserScope
@Component(dependencies = AppComponent.class, modules = TwitterModule.class)
public interface TwitterComponent {

    void inject(TweetRepositoryImpl tweetRepository);

    void inject(UserRepositoryImpl userRepository);
}
