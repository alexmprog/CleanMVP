package com.renovavision.cleanmvp.presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.interactors.TopArticlesInteractor;
import com.renovavision.cleanmvp.interactors.TopArticlesInteractorImpl;
import com.renovavision.cleanmvp.model.Article;
import com.renovavision.cleanmvp.ui.views.TopArticlesView;
import com.renovavision.cleanmvp.util.flow.FlowManager;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by alexmprog on 12.01.2016.
 */
public class TopArticlesPresenterImpl extends BasePresenterImpl implements TopArticlesPresenter {

    private static final String TAG = TopArticlesPresenterImpl.class.getSimpleName();

    @Inject
    FlowManager mFlowManager;

    @NonNull
    private WeakReference<TopArticlesView> mTopArticlesViewRef;

    @NonNull
    private TopArticlesInteractor mTopArticlesInteractor;

    private List<Article> mArticles;

    public TopArticlesPresenterImpl(@NonNull TopArticlesView topArticlesView, @NonNull Injectable injectable) {
        mTopArticlesViewRef = new WeakReference<>(topArticlesView);
        mTopArticlesInteractor = new TopArticlesInteractorImpl(injectable);
        injectable.getAppComponent().inject(this);
    }

    @Override
    public void onViewCreate() {
        TopArticlesView view = getView();
        if (view != null) {
            view.showProgress();
        }

        mTopArticlesInteractor.getTopArticles(new Callback<List<Article>>() {
            @Override
            public void success(Result<List<Article>> result) {
                mArticles = result.data;
                TopArticlesView view = getView();
                if (view != null) {
                    view.hideProgress();
                    view.showArticles(mArticles);
                }
            }

            @Override
            public void failure(TwitterException e) {
                Log.d(TAG, "Can not load articles", e);
                TopArticlesView view = getView();
                if (view != null) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    @Override
    public TopArticlesView getView() {
        return mTopArticlesViewRef.get();
    }

    @Override
    public void openArticle(int position) {
        if (mArticles == null || mArticles.isEmpty()) {
            return;
        }

        mArticles.get(position);

        // open article here
    }
}
