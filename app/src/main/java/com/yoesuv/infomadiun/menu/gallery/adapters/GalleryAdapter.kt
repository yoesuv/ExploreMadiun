package com.yoesuv.infomadiun.menu.gallery.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ItemGalleryBinding
import com.yoesuv.infomadiun.main.TransparentActivity
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.viewmodels.ItemGalleryViewModel
import java.lang.ref.WeakReference

/**
 *  Created by yusuf on 5/1/18.
 */
class GalleryAdapter(private val activity: Activity, private val listGallery: MutableList<GalleryModel>): RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private var inflater:LayoutInflater = LayoutInflater.from(activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemGalleryBinding = DataBindingUtil.inflate(inflater, R.layout.item_gallery, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listGallery.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.setData(activity, listGallery[fixPosition])
        holder.itemView.setOnLongClickListener {
            showPopUpImage(activity, listGallery[fixPosition])
            return@setOnLongClickListener true
        }
    }

    private fun showPopUpImage(activity: Activity?, model: GalleryModel){
        val intent = Intent(activity, TransparentActivity::class.java)
        intent.putExtra(TransparentActivity.EXTRA_DATA_IMAGE, model.image)
        activity?.startActivity(intent)
    }

    class ViewHolder(val binding: ItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(context: Context, gallery: GalleryModel){
            val weakContext = WeakReference(context)
            binding.itemGallery = ItemGalleryViewModel(weakContext, gallery)
        }
    }
}