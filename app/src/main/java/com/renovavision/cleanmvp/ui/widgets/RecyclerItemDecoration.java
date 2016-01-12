package com.renovavision.cleanmvp.ui.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by alexmprog on 12.01.2016.
 */
public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    private final int mSpace;

    public RecyclerItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace / 2;
        outRect.top = mSpace / 2;
        outRect.right = mSpace / 2;
        outRect.bottom = mSpace / 2;
    }
}
