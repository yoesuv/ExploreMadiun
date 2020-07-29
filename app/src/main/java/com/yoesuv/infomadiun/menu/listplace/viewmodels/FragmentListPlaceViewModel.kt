package com.yoesuv.infomadiun.menu.listplace.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.networks.db.repositories.DbPlaceRepository

class FragmentListPlaceViewModel(application: Application) : AndroidViewModel(application) {

    private val dbPlaceRepository = DbPlaceRepository(application.applicationContext)

    var listPlace: MutableLiveData<List<PlaceModel>> = MutableLiveData()

    fun getListPlace(){
        dbPlaceRepository.places {
            listPlace.postValue(it)
        }
    }

    fun getListPlaceKabMadiun(){

    }

    fun getListPlaceKabMagetan(){

    }

    fun getListPlaceKabNgawi(){

    }

    fun getListPlaceKabPacitan(){

    }

    fun getListPlaceKabPonorogo(){

    }

    fun getListPlaceKotaMadiun(){

    }

}