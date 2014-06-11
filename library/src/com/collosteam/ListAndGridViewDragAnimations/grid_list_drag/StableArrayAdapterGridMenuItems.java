package com.collosteam.ListAndGridViewDragAnimations.grid_list_drag;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Droid on 13.03.14.
 */

      public class StableArrayAdapterGridMenuItems extends ArrayAdapter<GridMenuItem> implements DraggableAdapter {

    final int INVALID_ID = -1;

    private final List<GridMenuItem> mObjects;
    HashMap<GridMenuItem, Integer> mIdMap = new HashMap<GridMenuItem, Integer>();

    int mTextViewResourceId;


    Drawable defaultBack;

    int localWidth = 400;


    public StableArrayAdapterGridMenuItems(Context context, int textViewResourceId, List<GridMenuItem> objects) {
        super(context, textViewResourceId, objects);
        mTextViewResourceId = textViewResourceId;
        mObjects = objects;
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }
        GridMenuItem item = getItem(position);
        return mIdMap.get(item);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CircleImageButton circleImageButton;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            circleImageButton = (CircleImageButton) mInflater.inflate(mTextViewResourceId, parent, false);
        } else {
            circleImageButton = (CircleImageButton) convertView;
        }
        GridMenuItem item = getItem(position);
        try {

            circleImageButton.setLayoutID(mTextViewResourceId);
            circleImageButton.setCount(item.getCounter());
            circleImageButton.setBackgroundColor(item.getCircleColor());
            circleImageButton.setResource(item.getImageResource());
             /* circleImageButton.setBackground(defaultBack);*/
            if (item instanceof CharSequence) {
                circleImageButton.setText((CharSequence) item.getTitle());
            } else {
                circleImageButton.setText(item.getTitle().toString());
            }

        } catch (ClassCastException e) {
            Log.e("ArrayAdapter", "You must supply a resource ID for a TextView");
            throw new IllegalStateException(
                    "ArrayAdapter requires the resource ID to be a TextView", e);
        }

        return circleImageButton;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void reorderElements(int position, int newPosition) {
        final List<GridMenuItem> objects = mObjects;

        GridMenuItem previous = objects.get(position);
        int iterator = newPosition < position ? 1 : -1;
        final int afterPosition = position + iterator;
        for (int cellPosition = newPosition; cellPosition != afterPosition; cellPosition += iterator) {
            GridMenuItem tmp = objects.get(cellPosition);
            objects.set(cellPosition, previous);
            previous = tmp;
        }
        notifyDataSetChanged();
    }

    @Override
    public void swapElements(int position, int newPosition) {
        final List<GridMenuItem> objects = mObjects;
        GridMenuItem temp = objects.get(position);
        objects.set(position, objects.get(newPosition));
        objects.set(newPosition, temp);
        notifyDataSetChanged();
    }
}

