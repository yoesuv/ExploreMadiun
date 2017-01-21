package com.yoesuv.infomadiun.utils;

import android.os.Build;
import android.text.Html;

/**
 * Created by yusuf on 1/21/17.
 */

public class ActivityHelper {

    @SuppressWarnings("deprecation")
    public static String fromHtml(String source){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            return String.valueOf(Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY));
        }else{
            return String.valueOf(Html.fromHtml(source));
        }
    }
}
