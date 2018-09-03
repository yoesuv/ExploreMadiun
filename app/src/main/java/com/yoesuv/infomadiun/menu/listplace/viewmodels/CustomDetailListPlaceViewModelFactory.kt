package com.yoesuv.infomadiun.menu.listplace.viewmodels

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel

class CustomDetailListPlaceViewModelFactory(private val placeModel: PlaceModel, private val application: Application): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailListPlaceViewModel(placeModel, application) as T
    }


}