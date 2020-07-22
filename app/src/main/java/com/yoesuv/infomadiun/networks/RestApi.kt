package com.yoesuv.infomadiun.networks

import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

/**
 *  Created by yusuf on 4/30/18.
 */
interface RestApi {

    @GET("List_place.json")
    fun getListPlace(): Observable<MutableList<PlaceModel>>

    @GET("List_place_kab_madiun.json")
    fun getListPlaceKabMadiun(): Observable<MutableList<PlaceModel>>

    @GET("List_place_kab_magetan.json")
    fun getListPlaceKabMagetan(): Observable<MutableList<PlaceModel>>

    @GET("List_place_kab_ngawi.json")
    fun getListPlaceKabNgawi(): Observable<MutableList<PlaceModel>>

    @GET("List_place_kab_pacitan.json")
    fun getListPlaceKabPacitan(): Observable<MutableList<PlaceModel>>

    @GET("List_place_kab_ponorogo.json")
    fun getListPlaceKabPonorogo(): Observable<MutableList<PlaceModel>>

    @GET("List_place_kota_madiun.json")
    fun getListPlaceKotaMadiun(): Observable<MutableList<PlaceModel>>

    @GET("Gallery_info.json")
    fun getListGallery(): Observable<MutableList<GalleryModel>>

    @GET("maps_info.json")
    fun getListPin(): Observable<MutableList<PinModel>>

    @GET("List_place.json")
    suspend fun listPlace(): Response<MutableList<PlaceModel>>

    @GET("Gallery_info.json")
    suspend fun gallery(): Response<MutableList<GalleryModel>>

    @GET("maps_info.json")
    suspend fun mapPins(): Response<MutableList<PinModel>>

}