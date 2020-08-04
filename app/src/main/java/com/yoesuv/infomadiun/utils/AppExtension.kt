package com.yoesuv.infomadiun.utils

import android.os.Build
import android.util.Log
import com.yoesuv.infomadiun.BuildConfig
import com.yoesuv.infomadiun.data.TAG_DEBUG
import com.yoesuv.infomadiun.data.TAG_ERROR

fun logDebug(message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG_DEBUG, message)
    }
}

fun logError(message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG_ERROR, message)
    }
}

fun lollipopOrNewer(body:() -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        body()
    }
}