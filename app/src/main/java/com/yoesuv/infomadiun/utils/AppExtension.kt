package com.yoesuv.infomadiun.utils

import android.os.Build
import android.util.Log
import com.yoesuv.infomadiun.BuildConfig
import com.yoesuv.infomadiun.data.Constants

fun logError(message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(Constants.TAG_ERROR, message)
    }
}

fun nougatOrBelow(body:() -> Unit) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        body()
    }
}