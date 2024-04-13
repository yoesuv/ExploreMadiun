package com.yoesuv.infomadiun.menu.maps.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "pins")
data class PinModel(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @SerializedName("name") @Expose val name: String?,
    @SerializedName("lokasi") @Expose val location: Int?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("image") @Expose val image: String?
)