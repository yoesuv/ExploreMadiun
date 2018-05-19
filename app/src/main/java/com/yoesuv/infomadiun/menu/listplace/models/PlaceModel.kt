package com.yoesuv.infomadiun.menu.listplace.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *  Created by yusuf on 4/30/18.
 */
data class PlaceModel(
        @SerializedName("nama") @Expose val name:String?,
        @SerializedName("lokasi") @Expose val location:String?,
        @SerializedName("kategori") @Expose val category:String?,
        @SerializedName("deskripsi") @Expose val description:String?,
        @SerializedName("thumbnail") @Expose val thumbnail:String?,
        @SerializedName("gambar") @Expose val image:String?)