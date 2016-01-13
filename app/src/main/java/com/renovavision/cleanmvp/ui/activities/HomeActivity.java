package com.renovavision.cleanmvp.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.model.Article;
import com.renovavision.cleanmvp.model.Profile;
import com.renovavision.cleanmvp.presenters.ProfilePresenter;
import com.renovavision.cleanmvp.presenters.impl.ProfilePresenterImpl;
import com.renovavision.cleanmvp.presenters.TopArticlesPresenter;
import com.renovavision.cleanmvp.presenters.impl.TopArticlesPresenterImpl;
import com.renovavision.cleanmvp.ui.adapters.ArticlesAdapter;
import com.renovavision.cleanmvp.ui.views.ProfileView;
import com.renovavision.cleanmvp.ui.views.TopArticlesView;
import com.renovavision.cleanmvp.ui.widgets.RecyclerItemClickListener;
import com.renovavision.cleanmvp.ui.widgets.RecyclerItemDecoration;
import com.renovavision.cleanmvp.util.imageprocessing.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class HomeActivity extends AppCompatActivity implements TopArticlesView, ProfileView {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.progress)
    ProgressBar mProgressBar;

    TextView mUsernameView;

    ImageView mUserProfileView;

    private ArticlesAdapter mAdapter;

    private TopArticlesPresenter mArticlesPresenter;
    private ProfilePresenter mProfilePresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initDrawerLayout();
        initToolbar(this);
        initRecyclerView();

        mArticlesPresenter = new TopArticlesPresenterImpl(this, (Injectable) getApplication());
        mArticlesPresenter.onViewCreate();

        mProfilePresenter = new ProfilePresenterImpl(this, (Injectable) getApplication());
        mProfilePresenter.onViewCreate();

    }

    private void initDrawerLayout() {
        mUsernameView = (TextView) mNavigationView.findViewById(R.id.username);
        mUserProfileView = (ImageView) mNavigationView.findViewById(R.id.avatar);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.top_images:
                        mProfilePresenter.openTopImages();
                        break;
                    case R.id.logout:
                        mProfilePresenter.logout();
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initToolbar(AppCompatActivity activity) {
        activity.setSupportActionBar(mToolbar);
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
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
                        mArticlesPresenter.openArticle(view, position);
                    }
                })
        );
    }

    @Override
    public void showArticles(List<Article> articlesList) {
        if (mAdapter == null) {
            mAdapter = new ArticlesAdapter(articlesList);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setItems(articlesList);
        }
    }

    @Override
    public void showProfile(Profile profile) {
        if (mUsernameView == null || mUserProfileView == null) {
            mUsernameView = (TextView) mNavigationView.findViewById(R.id.username);
            mUserProfileView = (ImageView) mNavigationView.findViewById(R.id.avatar);
        }

        mUsernameView.setText(profile.getName());
        Picasso.with(this)
                .load(profile.getProfileImageUrl())
                .transform(new CircleTransformation())
                .into(mUserProfileView);
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
