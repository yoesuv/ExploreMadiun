package com.yoesuv.infomadiun.menu.gallery.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import kotlinx.android.synthetic.main.item_gallery.view.*

/**
 *  Created by yusuf on 5/1/18.
 */
class GalleryAdapter(activity: Activity, private val listGallery: MutableList<GalleryModel>): RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private var inflater:LayoutInflater = LayoutInflater.from(activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View= inflater.inflate(R.layout.item_gallery, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listGallery.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(listGallery[holder.adapterPosition])
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun setData(gallery: GalleryModel){
            itemView.imageViewItemGallery.scaleType = ImageView.ScaleType.CENTER
            Glide.with(itemView.context.applicationContext)
                    .load(gallery.image)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.placeholder_image)
                            .error(R.drawable.placeholder_image)
                            .format(DecodeFormat.PREFER_ARGB_8888))
                    .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                    .into(itemView.imageViewItemGallery)
        }
    }
}