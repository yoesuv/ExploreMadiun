package com.yoesuv.infomadiun.menu.gallery.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *  Created by yusuf on 5/1/18.
 */
data class GalleryModel(
        @SerializedName("caption") @Expose val caption:String?,
        @SerializedName("thumbnail") @Expose val thumbnail:String?,
        @SerializedName("image") @Expose val image:String?
)