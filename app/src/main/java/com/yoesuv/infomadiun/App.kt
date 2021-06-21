package com.yoesuv.infomadiun

import android.app.Application
import com.yoesuv.infomadiun.utils.PreferencesHelper

/**
 *  Updated by yusuf on 02 June 2021.
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