package com.yoesuv.infomadiun.networks

import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import retrofit2.Response
import retrofit2.http.GET

/**
 *  Created by yusuf on 4/30/18.
 */
interface RestApi {

    @GET("List_place.json")
    suspend fun listPlace(): Response<MutableList<PlaceModel>>

    @GET("Gallery_info.json")
    suspend fun gallery(): Response<MutableList<GalleryModel>>

    @GET("maps_info.json")
    suspend fun mapPins(): Response<MutableList<PinModel>>

}