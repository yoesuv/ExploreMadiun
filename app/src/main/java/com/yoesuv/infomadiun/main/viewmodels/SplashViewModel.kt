package com.yoesuv.infomadiun.main.viewmodels

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yoesuv.infomadiun.BuildConfig
import com.yoesuv.infomadiun.main.MainActivity
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import com.yoesuv.infomadiun.networks.AppRepository
import com.yoesuv.infomadiun.networks.db.repositories.DbGalleryRepository
import com.yoesuv.infomadiun.networks.db.repositories.DbPinRepository
import com.yoesuv.infomadiun.networks.db.repositories.DbPlaceRepository
import com.yoesuv.infomadiun.utils.logDebug

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val dbPlaceRepository = DbPlaceRepository(application.applicationContext, viewModelScope)
    private val dbGalleryRepository = DbGalleryRepository(application.applicationContext)
    private val dbPinRepository = DbPinRepository(application.applicationContext)
    private val repo = AppRepository(viewModelScope)

    var version: ObservableField<String> = ObservableField(BuildConfig.VERSION_NAME)

    fun getAppData(activity: Activity) {
       repo.getSplashData({ places, galleries, pins ->
           logDebug("SplashViewModel # list place count ${places?.size}")
           logDebug("SplashViewModel # gallery count ${galleries?.size}")
           logDebug("SplashViewModel # map pins count ${pins?.size}")
           setupPlaces(places)
           setupGalleries(galleries)
           setupPins(pins)

           goToMain(activity)
       },{
           it.printStackTrace()
       })
    }

    private fun setupPlaces(places: List<PlaceModel>?) {
        places?.let {
            dbPlaceRepository.deleteAllPlace()
            it.forEach { place ->
                dbPlaceRepository.insertPlace(place)
            }
        }
    }

    private fun setupGalleries(galleries: List<GalleryModel>?) {
        galleries?.let {
            dbGalleryRepository.deleteAllGallery()
            it.forEach { gallery ->
                dbGalleryRepository.insertGallery(gallery)
            }
        }
    }

    private fun setupPins(pins: List<PinModel>?) {
        pins?.let {
            dbPinRepository.deleteAllPin()
            it.forEach { pin ->
                dbPinRepository.insertPin(pin)
            }
        }
    }

    private fun goToMain(activity: Activity) {
        val intent = Intent(activity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
        activity.finish()
    }

}