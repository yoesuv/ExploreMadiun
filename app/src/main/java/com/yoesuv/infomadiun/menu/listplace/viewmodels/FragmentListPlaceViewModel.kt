package com.yoesuv.infomadiun.menu.listplace.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.networks.ServiceFactory

class FragmentListPlaceViewModel: ViewModel() {

    private val restApi = ServiceFactory.create()

    var listPlace: MutableLiveData<MutableList<PlaceModel>> = MutableLiveData()

    var isLoading: ObservableField<Boolean> = ObservableField()
    var isError: ObservableField<Boolean> = ObservableField()

    fun getListPlace(){
        isLoading.set(true)
        isError.set(false)
    }

    fun getListPlaceKabMadiun(){
        isLoading.set(true)
        isError.set(false)
    }

    fun getListPlaceKabMagetan(){
        isLoading.set(true)
        isError.set(false)
    }

    fun getListPlaceKabNgawi(){
        isLoading.set(true)
        isError.set(false)
    }

    fun getListPlaceKabPacitan(){
        isLoading.set(true)
        isError.set(false)
    }

    fun getListPlaceKabPonorogo(){
        isLoading.set(true)
        isError.set(false)
    }

    fun getListPlaceKotaMadiun(){
        isLoading.set(true)
        isError.set(false)
    }

}