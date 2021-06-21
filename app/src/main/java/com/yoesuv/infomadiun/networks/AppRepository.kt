package com.yoesuv.infomadiun.networks

import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class AppRepository(private val scope: CoroutineScope): SafeApiRequest() {

    private val restApi = ServiceFactory.create()

    fun getSplashData(onSuccess:(MutableList<PlaceModel>?, MutableList<GalleryModel>?, MutableList<PinModel>?) -> Unit, onError:(Exception) -> Unit) {
        scope.launch {
            try {
                val resultListPlace = async { apiRequest { restApi.listPlace() } }
                val resultGallery = async { apiRequest { restApi.gallery() } }
                val resultMapPins = async { apiRequest { restApi.mapPins() } }

                onSuccess(resultListPlace.await(), resultGallery.await(), resultMapPins.await())
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

}