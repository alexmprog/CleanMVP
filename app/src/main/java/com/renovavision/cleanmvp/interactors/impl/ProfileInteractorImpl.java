package com.renovavision.cleanmvp.interactors.impl;

import android.support.annotation.NonNull;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.interactors.ProfileInteractor;
import com.renovavision.cleanmvp.model.Profile;
import com.renovavision.cleanmvp.repositories.UserRepository;
import com.renovavision.cleanmvp.repositories.impl.UserRepositoryImpl;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class ProfileInteractorImpl implements ProfileInteractor {

    @NonNull
    private final UserRepository mUserRepository;

    public ProfileInteractorImpl(@NonNull Injectable injectable) {
        this.mUserRepository = new UserRepositoryImpl(injectable);
    }

    @Override
    public void getUserProfile(@NonNull final Callback<Profile> callback) {
        mUserRepository.getUser(
                new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        callback.success(createProfile(result.data), result.response);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        callback.failure(e);
                    }
                });
    }

    private Profile createProfile(User data) {
        return new Profile("@" + data.screenName, data.profileImageUrl.replace("_normal", ""));
    }
}
