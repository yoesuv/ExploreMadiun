package com.yoesuv.infomadiun.menu.gallery.models

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 *  Created by yusuf on 5/1/18.
 */
@Keep
@Entity(tableName = "galleries")
@Parcelize
data class GalleryModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("caption") @Expose val caption: String?,
    @SerializedName("thumbnail") @Expose val thumbnail: String?,
    @SerializedName("image") @Expose val image: String?
) : Parcelable