package com.yoesuv.infomadiun.menu.gallery.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.*
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ItemGalleryBinding
import com.yoesuv.infomadiun.main.TransparentActivity
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.viewmodels.ItemGalleryViewModel

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
        holder.setData(listGallery[fixPosition])
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

    private fun revealShow(view: View, reveal: Boolean, alertDialog:AlertDialog){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            //val view: View = dialogView.findViewById(R.id.popupDetailListPlace)
            val w: Double = view.width.toDouble() - (view.width.toDouble()/2)
            val h: Double = view.height.toDouble() - (view.height.toDouble()/2)
            val endRadius: Float = Math.hypot(w, h).toFloat()

            if(reveal) {
                val anim: Animator = ViewAnimationUtils.createCircularReveal(view, w.toInt(), h.toInt(), 0F, endRadius)
                anim.start()
            }else{
                val anim: Animator = ViewAnimationUtils.createCircularReveal(view, w.toInt(), h.toInt(), endRadius, 0F)
                anim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        alertDialog.dismiss()
                    }
                })
                anim.start()
            }
        }
    }

    class ViewHolder(val binding: ItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(gallery: GalleryModel){
            binding.itemGallery = ItemGalleryViewModel(gallery)
        }
    }
}