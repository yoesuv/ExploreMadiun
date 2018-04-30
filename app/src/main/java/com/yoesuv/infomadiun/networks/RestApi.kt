package com.yoesuv.infomadiun.networks

import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *  Created by yusuf on 4/30/18.
 */
interface RestApi {

    @GET("393a7f8720b264649816/raw/ec756d2bd854fccb4597bf8801084f36fd682314//List_place.json")
    fun getListPlace(): Observable<MutableList<PlaceModel>>

}