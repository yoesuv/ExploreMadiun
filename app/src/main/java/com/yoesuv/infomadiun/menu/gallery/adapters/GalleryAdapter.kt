package com.yoesuv.infomadiun.menu.gallery.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.main.TransparentActivity
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import kotlinx.android.synthetic.main.item_gallery.view.*
import kotlinx.android.synthetic.main.popup_detail_list_place.view.*

/**
 *  Created by yusuf on 5/1/18.
 */
class GalleryAdapter(private val activity: Activity, private val listGallery: MutableList<GalleryModel>): RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private var inflater:LayoutInflater = LayoutInflater.from(activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View= inflater.inflate(R.layout.item_gallery, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listGallery.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.setData(listGallery[fixPosition])
        holder.itemView.setOnClickListener({
            showPopUp(activity, listGallery[fixPosition])
        })
        holder.itemView.setOnLongClickListener {
            showPopUpImage(activity, listGallery[fixPosition])
            return@setOnLongClickListener true
        }
    }

    private fun showPopUp(activity: Activity?, model: GalleryModel){
        val alertDialog: AlertDialog
        val ab:AlertDialog.Builder = AlertDialog.Builder(activity)
        val view:View = LayoutInflater.from(activity?.applicationContext).inflate(R.layout.popup_detail_list_place, null)
        ab.setView(view)
        view.layoutPopUpName.visibility = View.GONE
        view.textViewPopUpListPlaceDescription.text = model.caption
        view.imageViewPopupListPlace.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(activity?.applicationContext)
                .load(model.image)
                .apply(RequestOptions()
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.placeholder_image)
                        .format(DecodeFormat.PREFER_ARGB_8888))
                .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                .into(view.imageViewPopupListPlace)
        alertDialog = ab.create()
        alertDialog.setOnShowListener {
            revealShow(view, true, alertDialog)
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            alertDialog.setOnKeyListener(DialogInterface.OnKeyListener { _, i, _ ->
                if (i == KeyEvent.KEYCODE_BACK) {
                    revealShow(view, false, alertDialog)
                    return@OnKeyListener true
                }
                false
            })
            alertDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.setCancelable(false)
            alertDialog.setCanceledOnTouchOutside(false)
        }
        alertDialog.show()
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

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun setData(gallery: GalleryModel){
            itemView.imageViewItemGallery.scaleType = ImageView.ScaleType.CENTER_CROP
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