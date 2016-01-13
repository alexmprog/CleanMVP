package com.renovavision.cleanmvp.presenters.impl;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.interactors.TopImagesInteractor;
import com.renovavision.cleanmvp.interactors.impl.TopImagesInteractorImpl;
import com.renovavision.cleanmvp.model.Article;
import com.renovavision.cleanmvp.model.Image;
import com.renovavision.cleanmvp.presenters.TopImagesPresenter;
import com.renovavision.cleanmvp.ui.views.TopArticlesView;
import com.renovavision.cleanmvp.ui.views.TopImagesView;
import com.renovavision.cleanmvp.util.screen.ScreenManager;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by alexmprog on 13.01.2016.
 */
public class TopImagesPresenterImpl extends BasePresenterImpl implements TopImagesPresenter {

    private static final String TAG = TopImagesPresenterImpl.class.getSimpleName();

    @Inject
    ScreenManager mScreenManager;

    @NonNull
    private WeakReference<TopImagesView> mTopImagesViewRef;

    @NonNull
    private TopImagesInteractor mTopImageInteractor;

    private List<Image> mImages;

    public TopImagesPresenterImpl(@NonNull TopImagesView topImagesView, @NonNull Injectable injectable) {
        mTopImagesViewRef = new WeakReference<>(topImagesView);
        mTopImageInteractor = new TopImagesInteractorImpl(injectable);
        injectable.getScreenComponent().inject(this);
    }

    @Override
    public void onViewCreate() {
        TopImagesView view = getView();
        if (view == null) {
            return;
        }

        view.showProgress();

        mTopImageInteractor.getTopImages(new Callback<List<Image>>() {
            @Override
            public void success(Result<List<Image>> result) {
                mImages = result.data;
                TopImagesView view = getView();
                if (view != null) {
                    view.hideProgress();
                    view.showImages(mImages);
                }
            }

            @Override
            public void failure(TwitterException e) {
                Log.d(TAG, "Can not load images", e);
                TopImagesView view = getView();
                if (view != null) {
                    view.hideProgress();
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    @Override
    public TopImagesView getView() {
        return mTopImagesViewRef.get();
    }

    @Override
    public void openImage(View view, int position) {
        if (mImages == null || mImages.isEmpty()) {
            return;
        }

        mImages.get(position);

        Image image = mImages.get(position);
        if (image == null) {
            return;
        }

        TopImagesView imagesView = getView();
        if (imagesView == null) {
            return;
        }

        mScreenManager.openImageScreen(imagesView.getActivity(), image, imagesView.getTransitionAnimationView(view));
    }
}
