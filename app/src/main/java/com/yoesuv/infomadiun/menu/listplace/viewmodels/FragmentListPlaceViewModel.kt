package com.yoesuv.infomadiun.menu.listplace.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yoesuv.infomadiun.data.PlaceLocation
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.networks.db.repositories.DbPlaceRepository

class FragmentListPlaceViewModel(application: Application) : AndroidViewModel(application) {

    private val dbPlaceRepository = DbPlaceRepository(application.applicationContext, viewModelScope)

    var listPlace: MutableLiveData<List<PlaceModel>> = MutableLiveData()

    fun getListPlace(placeLocation: PlaceLocation) {
        if (placeLocation == PlaceLocation.ALL) {
            dbPlaceRepository.places {
                listPlace.postValue(it)
            }
        } else {
            dbPlaceRepository.placesByLocation(placeLocation.toString()) {
                listPlace.postValue(it)
            }
        }
    }

}