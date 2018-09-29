package com.yoesuv.infomadiun.widgets

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.yoesuv.infomadiun.data.Constants

/**
 *  Created by yusuf on 4/30/18.
 */
class ToolbarTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    init {
        val tf:Typeface = Typeface.createFromAsset(context.assets,Constants.FONT_PACIFICO)
        typeface = tf
    }
}
