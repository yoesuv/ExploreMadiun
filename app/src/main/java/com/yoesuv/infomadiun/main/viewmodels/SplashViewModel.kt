package com.yoesuv.infomadiun.main.viewmodels

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yoesuv.infomadiun.BuildConfig
import com.yoesuv.infomadiun.networks.AppRepository
import com.yoesuv.infomadiun.utils.logDebug

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = AppRepository(viewModelScope)

    var version: ObservableField<String> = ObservableField(BuildConfig.VERSION_NAME)

    fun getAppData(context: Context) {
       repo.getSplashData({ place, gallery, pins ->
           logDebug("SplashViewModel # list place count ${place?.size}")
           logDebug("SplashViewModel # gallery count ${gallery?.size}")
           logDebug("SplashViewModel # map pins count ${pins?.size}")
       },{
           it.printStackTrace()
       })
    }

}