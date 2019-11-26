package com.yoesuv.infomadiun.utils

import android.os.Build

fun nougatOrBelow(body:() -> Unit) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        body()
    }
}