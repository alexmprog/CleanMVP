package com.renovavision.cleanmvp.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.renovavision.cleanmvp.Injectable;
import com.renovavision.cleanmvp.R;
import com.renovavision.cleanmvp.model.Article;
import com.renovavision.cleanmvp.presenters.TopArticlesPresenter;
import com.renovavision.cleanmvp.presenters.TopArticlesPresenterImpl;
import com.renovavision.cleanmvp.ui.adapters.ArticlesAdapter;
import com.renovavision.cleanmvp.ui.views.TopArticlesView;
import com.renovavision.cleanmvp.ui.widgets.RecyclerItemClickListener;
import com.renovavision.cleanmvp.ui.widgets.RecyclerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class HomeActivity extends AppCompatActivity implements TopArticlesView {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.progress)
    ProgressBar mProgressBar;

    TextView usernameView;
    ImageView userProfileView;

    private ArticlesAdapter mAdapter;

    private TopArticlesPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initToolbar(this);
        initRecyclerView();

        mPresenter = new TopArticlesPresenterImpl(this, (Injectable) getApplication());
        mPresenter.onViewCreate();
    }

    private void initToolbar(AppCompatActivity activity) {
        activity.setSupportActionBar(mToolbar);
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
                        mPresenter.openArticle(position);
                    }
                })
        );
    }

    @Override
    public void showArticles(List<Article> articlesList) {
        mAdapter = new ArticlesAdapter(articlesList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String mMessage) {
        Snackbar.make(mRecyclerView, R.string.try_again, Snackbar.LENGTH_SHORT).show();
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
    public Context getContext() {
        return this;
    }

    @Override
    public void finishView() {
        finish();
    }
}
