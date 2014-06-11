package com.collosteam.ListAndGridViewDragAnimations.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David com.ditfly.app.core 23 Aug 2013
 */
public class SettingsManager {

    public static final String PK_MENUORDER = "app.menu.order";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Context context;

    public SettingsManager(Context context) {
        this.context = context;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = prefs.edit();
    }

	/*---menu ordering---*/

    public void setMenuOrdering(long[] ids) {
        String orderstring = ApiUtils.makeSplitParams(ids);
        editor.putString(PK_MENUORDER, orderstring);
        editor.commit();
    }

    public boolean isMenuOrdered() {
        boolean exist = prefs.contains(PK_MENUORDER);
        boolean notempty = !TextUtils.isEmpty(prefs.getString(PK_MENUORDER, new String()));
        return exist && notempty;
    }

    public void clearMenuOrdering() {
        editor.remove(PK_MENUORDER);
        editor.commit();
    }

    public List<Long> getMenuOrder() {
        String filter = prefs.getString(PK_MENUORDER, new String());
        String[] filters = filter.split(",");
        List<Long> order = new ArrayList<Long>();
        for (int i = 0; i < filters.length; i++) {
            if (!TextUtils.isEmpty(filters[i]))
                order.add(Long.parseLong(filters[i]));
        }
        return order;
    }


}
