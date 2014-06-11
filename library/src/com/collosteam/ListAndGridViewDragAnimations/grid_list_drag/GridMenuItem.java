package com.collosteam.ListAndGridViewDragAnimations.grid_list_drag;

import android.view.View;

/**
 * Created by Droid on 13.03.14.
 */
public abstract class GridMenuItem {

    public abstract long getId();

    public abstract String getTitle();

    public abstract int getImageResource();

    public abstract int getCircleColor();

    public abstract void onClickAction();

    public abstract boolean isOdd();

    public abstract int getCounter();

    public View.OnClickListener getGetClickAction() {
        return mClickAction;
    }

    View.OnClickListener mClickAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onClickAction();
        }
    };
}
