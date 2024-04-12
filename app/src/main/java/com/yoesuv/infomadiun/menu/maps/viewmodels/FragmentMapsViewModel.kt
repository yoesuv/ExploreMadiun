package com.yoesuv.infomadiun.menu.maps.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import com.yoesuv.infomadiun.networks.db.repositories.DbPinRepository

class FragmentMapsViewModel(application: Application) : AndroidViewModel(application) {

    private val dbPin = DbPinRepository(application.applicationContext, viewModelScope)

    var listPin: MutableLiveData<List<PinModel>> = MutableLiveData()

    fun getListPin() {
        dbPin.pins {
            listPin.postValue(it)
        }
    }

}