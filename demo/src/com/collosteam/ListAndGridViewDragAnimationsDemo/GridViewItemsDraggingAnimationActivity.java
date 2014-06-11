/*
 * Copyright (C) 2013 The Android Open Source Project
 * Copyright (C) 2013 Jacek Marchwicki <jacek.marchwicki@gmail.com>
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

package com.collosteam.ListAndGridViewDragAnimationsDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.collosteam.ListAndGridViewDragAnimations.grid_list_drag.DynamicGridViewItems;
import com.collosteam.ListAndGridViewDragAnimations.grid_list_drag.GridMenuItem;
import com.collosteam.ListAndGridViewDragAnimations.grid_list_drag.StableArrayAdapterGridMenuItems;
import com.collosteam.ListAndGridViewDragAnimations.utils.SettingsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This application creates a listview where the ordering of the data set
 * can be modified in response to user touch events.
 * <p/>
 * An item in the listview is selected via a long press event and is then
 * moved around by tracking and following the movement of the user's finger.
 * When the item is released, it animates to its new position within the listview.
 */
public class GridViewItemsDraggingAnimationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_item);
        SettingsManager sm = new SettingsManager(this);
        ArrayList<GridMenuItem> mCheeseList = new ArrayList<GridMenuItem>();
        mCheeseList.add(getNullMenu());
        mCheeseList.add(getNullMenu1());
        for (int i = 0; i < Cheeses.sCheeseStrings.length; ++i) {
            mCheeseList.add(getNullMenu(i));
        }
        if (sm.isMenuOrdered()) {
            List<Long> order = sm.getMenuOrder();
            for (int i = 0; i < mCheeseList.size(); i++) {
                GridMenuItem item = mCheeseList.get(i);
                int index = order.indexOf(item.getId());
                if (index != -1)
                    try {
                        swapItems(i, index,mCheeseList );
                    } catch (IndexOutOfBoundsException e) {
                        swapItems(i, 0,mCheeseList); // addet 19.08
                    }
            }
        }
        StableArrayAdapterGridMenuItems adapter = new StableArrayAdapterGridMenuItems(this, R.layout.item_drag_grid_view, mCheeseList);
        DynamicGridViewItems gridView = (DynamicGridViewItems) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
        gridView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void swapItems(int itemIndexA, int itemIndexB, ArrayList<GridMenuItem> cheeseList) {
        Collections.swap(cheeseList, itemIndexA, itemIndexB);
    }

    private GridMenuItem getNullMenu() {

        return new GridMenuItem() {

            @Override
            public long getId() {
                return -100;
            }

            @Override
            public String getTitle() {
                return "I am SMS";
            }

            @Override
            public int getImageResource() {
                return R.drawable.ic_launcher_smsmms;
            }

            @Override
            public int getCircleColor() {
                return R.color.dit_yellow;
            }

            @Override
            public void onClickAction() {
                Toast.makeText(getApplicationContext(), " S M S ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean isOdd() {
                return false;
            }


            @Override
            public int getCounter() {
                return 666;
            }
        };
    }

    private GridMenuItem getNullMenu1() {

        return new GridMenuItem() {

            @Override
            public long getId() {
                return -101;
            }

            @Override
            public String getTitle() {
                return "I am SMS 2";
            }

            @Override
            public int getImageResource() {
                return R.drawable.ic_launcher_smsmms;
            }

            @Override
            public int getCircleColor() {
                return R.color.dit_nude;
            }

            @Override
            public void onClickAction() {
                Toast.makeText(getApplicationContext(), " S M S 2", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean isOdd() {
                return false;
            }


            @Override
            public int getCounter() {
                return 9990;
            }
        };
    }


    private GridMenuItem getNullMenu(final int position) {

        return new GridMenuItem() {

            @Override
            public long getId() {
                return position;
            }

            @Override
            public String getTitle() {
                return Cheeses.sCheeseStrings[position];
            }

            @Override
            public int getImageResource() {
                return R.drawable.ic_launcher;
            }

            @Override
            public int getCircleColor() {
                return R.color.dit_nude;
            }


            @Override
            public void onClickAction() {
                Toast.makeText(getApplicationContext(), " Android "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean isOdd() {
                return false;
            }


            @Override
            public int getCounter() {
                return position;
            }
        };
    }

}
