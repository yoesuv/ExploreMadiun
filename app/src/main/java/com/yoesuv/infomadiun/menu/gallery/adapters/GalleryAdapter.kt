package com.yoesuv.infomadiun.menu.gallery.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yoesuv.infomadiun.menu.gallery.adapters.viewholders.GalleryViewHolder
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.utils.AdapterCallback

/**
 *  Updated by yusuf on 26 July 2020.
 */
class GalleryAdapter(val onItemClick: (galleryModel: GalleryModel) -> Unit) :
    ListAdapter<GalleryModel, GalleryViewHolder>(AdapterCallback.diffGalleryCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val galleryModel = getItem(position)
        holder.bind(galleryModel)
        holder.itemView.setOnClickListener {
            onItemClick(galleryModel)
        }
    }

}