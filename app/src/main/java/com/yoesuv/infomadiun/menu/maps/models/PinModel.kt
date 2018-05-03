package com.yoesuv.infomadiun.menu.maps.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PinModel(
        @SerializedName("name") @Expose val name:String?,
        @SerializedName("lokasi") @Expose val location:Int?,
        @SerializedName("latitude") val latitude:Float,
        @SerializedName("longitude") val longitude:Float,
        @SerializedName("image") @Expose val image:String?
)