package com.yoesuv.infomadiun.menu.listplace.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel

class CustomDetailListPlaceViewModelFactory(private val application: Application, private val placeModel: PlaceModel): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailListPlaceViewModel(application, placeModel) as T
    }

}