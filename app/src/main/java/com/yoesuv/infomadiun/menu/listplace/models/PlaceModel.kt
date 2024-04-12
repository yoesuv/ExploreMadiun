package com.yoesuv.infomadiun.menu.listplace.models

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 *  Created by yusuf on 4/30/18.
 */
@Keep
@Entity(tableName = "places")
@Parcelize
data class PlaceModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("nama") @Expose val name: String?,
    @SerializedName("lokasi") @Expose val location: String?,
    @SerializedName("kategori") @Expose val category: String?,
    @SerializedName("deskripsi") @Expose val description: String?,
    @SerializedName("thumbnail") @Expose val thumbnail: String?,
    @SerializedName("gambar") @Expose val image: String?
) : Parcelable