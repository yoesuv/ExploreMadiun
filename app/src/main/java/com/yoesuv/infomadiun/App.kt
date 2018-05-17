package com.yoesuv.infomadiun

import android.app.Application
import com.yoesuv.infomadiun.utils.PreferencesHelper

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
    }

}