package com.yoesuv.infomadiun.menu.maps.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import com.yoesuv.infomadiun.networks.ServiceFactory

class FragmentMapsViewModel(application: Application) : AndroidViewModel(application) {

    private var restApi = ServiceFactory.create()

    var listPin: MutableLiveData<MutableList<PinModel>> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    fun getListPin(){

    }

}