package com.yoesuv.infomadiun.menu.maps.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PinModel(
        @SerializedName("name") @Expose val name:String?,
        @SerializedName("lokasi") @Expose val location:Int?,
        @SerializedName("latitude") val latitude:Double?,
        @SerializedName("longitude") val longitude:Double?,
        @SerializedName("image") @Expose val image:String?
)