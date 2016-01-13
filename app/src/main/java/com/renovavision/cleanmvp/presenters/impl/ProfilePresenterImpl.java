package com.renovavision.cleanmvp.presenters.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.interactors.ProfileInteractor;
import com.renovavision.cleanmvp.interactors.impl.ProfileInteractorImpl;
import com.renovavision.cleanmvp.model.Profile;
import com.renovavision.cleanmvp.presenters.ProfilePresenter;
import com.renovavision.cleanmvp.ui.views.ProfileView;
import com.renovavision.cleanmvp.util.screen.ScreenManager;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by alexmprog on 13.01.2016.
 */
public class ProfilePresenterImpl extends BasePresenterImpl implements ProfilePresenter {

    private static final String TAG = ProfilePresenterImpl.class.getSimpleName();

    @Inject
    ScreenManager mScreenManager;

    @NonNull
    private WeakReference<ProfileView> mProfileViewRef;

    @NonNull
    private ProfileInteractor mProfileInteractor;

    public ProfilePresenterImpl(@NonNull ProfileView profileView, @NonNull Injectable injectable) {
        mProfileViewRef = new WeakReference<>(profileView);
        mProfileInteractor = new ProfileInteractorImpl(injectable);
        injectable.getScreenComponent().inject(this);
    }

    @Override
    public void onViewCreate() {
        mProfileInteractor.getProfile(new Callback<Profile>() {
            @Override
            public void success(Result<Profile> result) {
                ProfileView view = getView();
                if (view != null) {
                    view.showProfile(result.data);
                }
            }

            @Override
            public void failure(TwitterException e) {
                Log.d(TAG, "Can not load profile", e);
                ProfileView view = getView();
                if (view != null) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    @Override
    public ProfileView getView() {
        return mProfileViewRef.get();
    }

    @Override
    public void openTopImages() {
        ProfileView view = getView();
        if (view == null) {
            return;
        }

        mScreenManager.openTopImagesScreen(view.getContext());
    }

    @Override
    public void logout() {
        ProfileView view = getView();
        if (view == null) {
            return;
        }

        TwitterCore.getInstance().logOut();
        mScreenManager.openSplashScreen(view.getContext());
        view.finishView();
    }
}
