package com.yoesuv.infomadiun

import android.app.Application
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.utils.PreferencesHelper
import io.fabric.sdk.android.Fabric

/**
 *  Created by yusuf on 4/28/18.
 */
class App : Application() {

    companion object {
        var prefHelper: PreferencesHelper? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefHelper = PreferencesHelper(this)
        if(BuildConfig.BUILD_TYPE.equals("release", ignoreCase = true)) {
            Log.d(Constants.TAG_DEBUG,"App # onCreate => build type release")
            Fabric.with(this, Crashlytics())
        }else{
            Log.d(Constants.TAG_DEBUG,"App # onCreate => build type debug")
        }
    }

}