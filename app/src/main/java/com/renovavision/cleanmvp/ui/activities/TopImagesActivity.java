package com.renovavision.cleanmvp.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.model.Image;
import com.renovavision.cleanmvp.presenters.TopImagesPresenter;
import com.renovavision.cleanmvp.presenters.impl.TopImagesPresenterImpl;
import com.renovavision.cleanmvp.ui.adapters.ImagesAdapter;
import com.renovavision.cleanmvp.ui.views.TopImagesView;
import com.renovavision.cleanmvp.ui.widgets.RecyclerItemClickListener;
import com.renovavision.cleanmvp.ui.widgets.RecyclerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexmprog on 04.01.2016.
 */
public class TopImagesActivity extends AppCompatActivity implements TopImagesView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.progress)
    ProgressBar mProgressBar;

    @NonNull
    private TopImagesPresenter mImagesPresenter;

    private ImagesAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        ButterKnife.bind(this);

        initToolbar(this);
        initRecyclerView();

        mImagesPresenter = new TopImagesPresenterImpl(this, (Injectable) getApplication());
        mImagesPresenter.onViewCreate();
    }

    private void initToolbar(AppCompatActivity activity) {
        activity.setSupportActionBar(mToolbar);
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.scrollToPosition(0);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RecyclerItemDecoration(getResources()
                .getDimensionPixelSize(R.dimen.activity_main_recycler_divider)));
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mImagesPresenter.openImage(view, position);
                    }
                })
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showImages(List<Image> imageList) {
        if (mAdapter == null) {
            mAdapter = new ImagesAdapter(imageList);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setItems(imageList);
        }
    }

    @Override
    public void showMessage(String message) {
        if (TextUtils.isEmpty(message)) {
            message = getString(R.string.try_again);
        }
        Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public Activity getActivity() {
        return this;
    }

    @NonNull
    @Override
    public View getTransitionAnimationView(View view) {
        return view.findViewById(R.id.container_inner_item);
    }

    @NonNull
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finishView() {
        finish();
    }
}
