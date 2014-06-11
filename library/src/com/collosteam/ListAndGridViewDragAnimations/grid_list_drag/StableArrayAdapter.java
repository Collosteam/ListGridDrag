/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import com.collosteam.ListAndGridViewDragAnimations.R;

import java.util.HashMap;
import java.util.List;

public class StableArrayAdapter extends ArrayAdapter<String> implements DraggableAdapter {

    final int INVALID_ID = -1;

    private final List<String> mObjects;
    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    int mTextViewResourceId;


    Drawable defaultBack;

    int localWidth = 400;


    public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
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
        String item = getItem(position);
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
        Object item = getItem(position);
        try {
            circleImageButton.setLayoutID(mTextViewResourceId);
            circleImageButton.setCount(position - 10);
            if (position % 2 == 0) {
                circleImageButton.setBackgroundColor(R.color.dit_yellow);

            }circleImageButton.setResource(R.drawable.ic_launcher_smsmms);
           /* circleImageButton.setBackground(defaultBack);*/
            if (item instanceof CharSequence) {
                circleImageButton.setText((CharSequence) item);
            } else {
                circleImageButton.setText(item.toString());
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
        final List<String> objects = mObjects;

        String previous = objects.get(position);
        int iterator = newPosition < position ? 1 : -1;
        final int afterPosition = position + iterator;
        for (int cellPosition = newPosition; cellPosition != afterPosition; cellPosition += iterator) {
            String tmp = objects.get(cellPosition);
            objects.set(cellPosition, previous);
            previous = tmp;
        }
        notifyDataSetChanged();
    }

    @Override
    public void swapElements(int position, int newPosition) {
        final List<String> objects = mObjects;
        String temp = objects.get(position);
        objects.set(position, objects.get(newPosition));
        objects.set(newPosition, temp);
        notifyDataSetChanged();
    }
}
