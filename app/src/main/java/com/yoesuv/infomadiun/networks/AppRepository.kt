package com.yoesuv.infomadiun.networks

import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class AppRepository(private val scope: CoroutineScope): SafeApiRequest() {

    private val restApi = ServiceFactory.create()

    fun getSplashData(onSuccess:(MutableList<PlaceModel>?, MutableList<GalleryModel>?, MutableList<PinModel>?) -> Unit, onError:(Exception) -> Unit) {
        scope.launch {
            try {
                val resultListPlace = apiRequest { restApi.listPlace() }
                val resultGallery = apiRequest { restApi.gallery() }
                val resultMapPins = apiRequest { restApi.mapPins() }

                onSuccess(resultListPlace, resultGallery, resultMapPins)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

}