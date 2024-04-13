package com.yoesuv.infomadiun.utils

import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.LocationManager
import android.os.Build
import android.text.Html
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority

/**
 *  Updated by yusuf on  12 July 2020
 */

object AppHelper {

    fun displayNormalToast(context: Context, @StringRes message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun displayErrorToast(context: Context, @StringRes message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun checkLocationSetting(context: Context): Boolean {
        val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    /**
     * https://stackoverflow.com/a/48326744
     */
    fun displayLocationSettingsRequest(activity: Activity) {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
            .setWaitForAccurateLocation(false)
            .build()
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)

        val result = LocationServices.getSettingsClient(activity).checkLocationSettings(builder.build())
        result.addOnCompleteListener { task ->
            try {
                task.getResult(ApiException::class.java)
            } catch (ex: ApiException) {
                if (ex.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                    val resolvableApiException = ex as ResolvableApiException
                    try {
                        resolvableApiException.startResolutionForResult(activity, 27)
                    } catch (e: IntentSender.SendIntentException) {
                        logError("FragmentMaps # RESOLUTION_REQUIRED ${e.message}")
                    }
                } else if (ex.statusCode == LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE) {
                    logError("FragmentMaps # LocationSettings DISABLED")
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    fun fromHtml(source: String?): String {
        return if (source != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                Html.fromHtml(source).toString()
            }
        } else {
            ""
        }
    }

}
