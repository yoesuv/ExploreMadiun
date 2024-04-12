package com.yoesuv.infomadiun.utils

import androidx.recyclerview.widget.DiffUtil
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel
import com.yoesuv.infomadiun.menu.other.models.LicenseModel

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

    val diffChangeLogCallback = object : DiffUtil.ItemCallback<ChangeLogModel>() {
        override fun areItemsTheSame(oldItem: ChangeLogModel, newItem: ChangeLogModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ChangeLogModel, newItem: ChangeLogModel): Boolean {
            return oldItem == newItem
        }
    }

    val diffLicenseCallback = object : DiffUtil.ItemCallback<LicenseModel>() {
        override fun areItemsTheSame(oldItem: LicenseModel, newItem: LicenseModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: LicenseModel, newItem: LicenseModel): Boolean {
            return oldItem == newItem
        }
    }

}