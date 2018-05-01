package com.yoesuv.infomadiun.utils

import com.yoesuv.infomadiun.R
import android.content.Context
import android.content.res.TypedArray

/**
 *  Created by yusuf on 5/1/18.
 */

object AppHelper {

    fun getToolbarHeight(context: Context): Int {
        val styledAttributes = context.theme.obtainStyledAttributes(
                intArrayOf(R.attr.actionBarSize))
        val toolbarHeight = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()

        return toolbarHeight
    }

}
