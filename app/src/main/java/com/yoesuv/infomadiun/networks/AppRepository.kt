package com.yoesuv.infomadiun.networks

import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import java.lang.Exception

class AppRepository {

    private val restApi = ServiceFactory.create()

    suspend fun getSplashData(onSuccess: (MutableList<PlaceModel>?, MutableList<GalleryModel>?, MutableList<PinModel>?) -> Unit, onError: (Exception) -> Unit) {
        try {
            val resultListPlace = restApi.listPlace()
            val resultGallery = restApi.gallery()
            val resultMapPins = restApi.mapPins()
            onSuccess(resultListPlace.body(), resultGallery.body(), resultMapPins.body())
        } catch (e: Exception) {
            onError(e)
        }
    }

}