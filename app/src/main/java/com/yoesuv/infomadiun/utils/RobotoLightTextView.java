package com.yoesuv.infomadiun.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 *  Created by yusuf on 6/1/17.
 */

public class RobotoLightTextView extends AppCompatTextView{

    public RobotoLightTextView(Context context) {
        super(context);
        init();
    }

    public RobotoLightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoLightTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"roboto-light.ttf");
        setTypeface(tf);
    }
}
