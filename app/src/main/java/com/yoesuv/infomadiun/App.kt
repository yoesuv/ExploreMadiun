package com.yoesuv.infomadiun

import android.app.Application
import com.crashlytics.android.Crashlytics
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
        Fabric.with(this, Crashlytics())
    }

}