package com.yoesuv.infomadiun.networks

import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *  Created by yusuf on 4/30/18.
 */
interface RestApi {

    @GET("393a7f8720b264649816/raw/ec756d2bd854fccb4597bf8801084f36fd682314/List_place.json")
    fun getListPlace(): Observable<MutableList<PlaceModel>>

    @GET("105e37d90933191fda15/raw/1a8092436fed2a2f45b58e9d1c73ffff86b41a0f/List_place_kab_madiun.json")
    fun getListPlaceKabMadiun(): Observable<MutableList<PlaceModel>>

    @GET("360fb6e5b0af5be86ac6/raw/5da0927b4b2ecf7d7e649e0fa2c0bbaef3c3ae6d/List_place_kab_magetan.json")
    fun getListPlaceKabMagetan(): Observable<MutableList<PlaceModel>>

    @GET("b7bf0459f7f1ef0f2e74/raw/c0b8f43097fcbfae9a6cc9b6625bd0e5aca2be78/List_place_kab_ngawi.json")
    fun getListPlaceKabNgawi(): Observable<MutableList<PlaceModel>>

    @GET("4e715cce01aaadbb0683/raw/0e15363abb192f7e16527acee6470bd9c1f9004b/List_place_kab_pacitan.json")
    fun getListPlaceKabPacitan(): Observable<MutableList<PlaceModel>>

    @GET("105f88724e530af23613/raw/9d93f9afb90c1b0f4b8c99d93bdff3460197c0c6/List_place_kab_ponorogo.json")
    fun getListPlaceKabPonorogo(): Observable<MutableList<PlaceModel>>

    @GET("d73bc7905e006cf99397/raw/4b500985da0feaba42f7617a425663b7f3390930/List_place_kota_madiun.json")
    fun getListPlaceKotaMadiun(): Observable<MutableList<PlaceModel>>

    @GET("c6d873e4243da86b8886/raw/b7912de5a9c917e8bea1b7dd7b178197878f78fc/Gallery_info.json")
    fun getListGallery(): Observable<MutableList<GalleryModel>>

    @GET("fb28d18f05f470e72b3d/raw/12adff90dea9cb9622c3e792319cf2dc6d2e286f/maps_info.json")
    fun getListPin(): Observable<MutableList<PinModel>>

}