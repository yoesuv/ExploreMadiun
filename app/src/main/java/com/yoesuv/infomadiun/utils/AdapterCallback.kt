package com.yoesuv.infomadiun.utils

import androidx.recyclerview.widget.DiffUtil
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel

object AdapterCallback {

    val diffPlaceCallback = object : DiffUtil.ItemCallback<PlaceModel>() {
        override fun areItemsTheSame(oldItem: PlaceModel, newItem: PlaceModel): Boolean {
            return oldItem.name == newItem.name
        }
        override fun areContentsTheSame(oldItem: PlaceModel, newItem: PlaceModel): Boolean {
            return oldItem == newItem
        }
    }

    val diffGalleryCallback = object : DiffUtil.ItemCallback<GalleryModel>() {
        override fun areItemsTheSame(oldItem: GalleryModel, newItem: GalleryModel): Boolean {
            return oldItem.caption == newItem.caption
        }
        override fun areContentsTheSame(oldItem: GalleryModel, newItem: GalleryModel): Boolean {
            return oldItem == newItem
        }
    }

}