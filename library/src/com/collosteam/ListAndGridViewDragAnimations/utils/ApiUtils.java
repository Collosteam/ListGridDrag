package com.collosteam.ListAndGridViewDragAnimations.utils;

/**
 * @author David com.ditfly.api 23 Aug 2013
 */
public class ApiUtils {

    public static String makeSplitParams(long... ids) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            sb.append(ids[i]);
            if (!(i == ids.length - 1))
                sb.append(",");
        }
        return sb.toString();
    }

}
