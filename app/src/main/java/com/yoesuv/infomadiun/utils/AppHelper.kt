package com.yoesuv.infomadiun.utils

import com.yoesuv.infomadiun.R
import android.content.Context
import android.location.LocationManager
import android.os.Build
import android.text.Html

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

    fun checkLocationSetting(context: Context):Boolean{
        val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    @Suppress("DEPRECATION")
    fun fromHtml(source: String): String{
        return if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY).toString()
        }else{
            Html.fromHtml(source).toString()
        }
    }

}
