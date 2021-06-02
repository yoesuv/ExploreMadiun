package com.yoesuv.infomadiun

import android.app.Application
import com.yoesuv.infomadiun.utils.PreferencesHelper
import io.sentry.SentryLevel
import io.sentry.android.core.SentryAndroid

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
        initSentry()
    }

    private fun initSentry() {
        if (!BuildConfig.DEBUG) {
            SentryAndroid.init(this) { options ->
                options.setBeforeSend { event, _ ->
                    if (SentryLevel.DEBUG == event.level) {
                        return@setBeforeSend null
                    } else {
                        return@setBeforeSend event
                    }
                }
            }
        }
    }

}