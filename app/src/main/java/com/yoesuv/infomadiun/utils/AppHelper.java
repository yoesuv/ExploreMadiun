package com.yoesuv.infomadiun.utils;

import com.yoesuv.infomadiun.R;
import android.content.Context;
import android.content.res.TypedArray;

/**
 *  Created by yusuf on 5/1/18.
 */

public class AppHelper {

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

}
