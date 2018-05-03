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

    @GET("c6d873e4243da86b8886/raw/b7912de5a9c917e8bea1b7dd7b178197878f78fc/Gallery_info.json")
    fun getListGallery(): Observable<MutableList<GalleryModel>>

    @GET("fb28d18f05f470e72b3d/raw/12adff90dea9cb9622c3e792319cf2dc6d2e286f/maps_info.json")
    fun getListPin(): Observable<MutableList<PinModel>>

}