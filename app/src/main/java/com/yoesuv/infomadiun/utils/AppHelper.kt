package com.yoesuv.infomadiun.utils

import android.content.Context
import android.location.LocationManager
import android.os.Build
import android.text.Html
import android.widget.Toast
import es.dmoral.toasty.Toasty

/**
 *  Updated by yusuf on  12 July 2020
 */

object AppHelper {

    fun displayNormalToast(context: Context, message: String){
        Toasty.normal(context, message, Toast.LENGTH_SHORT).show()
    }

    fun displayErrorToast(context: Context, message: String){
        Toasty.error(context, message, Toast.LENGTH_SHORT, true).show()
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
