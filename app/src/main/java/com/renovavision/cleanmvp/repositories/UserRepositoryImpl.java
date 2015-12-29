package com.renovavision.cleanmvp.repositories;


import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.api.CustomTwitterApi;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

import javax.inject.Inject;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class UserRepositoryImpl implements UserRepository {

    @Inject
    CustomTwitterApi mTwitterApi;

    public UserRepositoryImpl(@NonNull Injectable injectable) {
        injectable.getTwitterComponent().inject(this);
    }

    @Override
    public void getUser(@NonNull final Callback<User> callback) {
        mTwitterApi.getUser(
                new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        callback.success(result.data, result.response);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        callback.failure(e);
                    }
                });
    }
}
