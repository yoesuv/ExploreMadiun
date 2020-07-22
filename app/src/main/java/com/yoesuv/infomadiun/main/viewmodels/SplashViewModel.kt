package com.yoesuv.infomadiun.main.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.yoesuv.infomadiun.BuildConfig

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    var version: ObservableField<String> = ObservableField(BuildConfig.VERSION_NAME)

}