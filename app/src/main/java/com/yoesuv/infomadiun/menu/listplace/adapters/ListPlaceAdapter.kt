package com.yoesuv.infomadiun.menu.listplace.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.main.TransparentActivity
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import kotlinx.android.synthetic.main.item_list_place.view.*
import kotlinx.android.synthetic.main.popup_detail_list_place.view.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

/**
 *  Created by yusuf on 4/30/18.
 */
class ListPlaceAdapter(private val activity: Activity, private val listPlace:MutableList<PlaceModel>, private val recyclerView: RecyclerView?): RecyclerView.Adapter<ListPlaceAdapter.ViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = inflater.inflate(R.layout.item_list_place, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPlace.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fixPos:Int = holder.adapterPosition
        holder.setData(listPlace[fixPos])
        holder.itemView.setOnClickListener {
            showPopUp(activity, listPlace[fixPos])
        }
        holder.itemView.setOnLongClickListener {
            showPopUpImage(activity, listPlace[fixPos])
            return@setOnLongClickListener true
        }
    }

    private fun showPopUp(activity: Activity?, model: PlaceModel){
        val alertDialog:AlertDialog
        val ab:AlertDialog.Builder = AlertDialog.Builder(activity)
        val view:View = LayoutInflater.from(activity?.applicationContext).inflate(R.layout.popup_detail_list_place, null)
        ab.setView(view)
        view.textViewPopUpListPlaceName.text = model.name
        view.textViewPopUpListPlaceDescription.text = model.description
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
            Log.d(Constants.TAG_DEBUG,"ListPlaceAdapter # dialog on show")
            revealShow(view, true, alertDialog)
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
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

    private fun showPopUpImage(activity: Activity?, model: PlaceModel){
        recyclerView?.stopScroll()
        val intent = Intent(activity, TransparentActivity::class.java)
        intent.putExtra(TransparentActivity.EXTRA_DATA_IMAGE, model.image)
        activity?.startActivity(intent)
    }

    private fun revealShow(view: View, reveal: Boolean, alertDialog:AlertDialog){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
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

        fun setData(placeModel: PlaceModel){
            launch {
                withContext(UI){
                    itemView.textViewItemListPlaceName.text = placeModel.name
                    itemView.textViewItemListPlaceLocation.text = placeModel.location
                    itemView.imageViewItemListPlace.scaleType = ImageView.ScaleType.CENTER_CROP
                    Glide.with(itemView)
                            .load(placeModel.image)
                            .apply(RequestOptions()
                                    .placeholder(R.drawable.placeholder_image)
                                    .error(R.drawable.placeholder_image)
                                    .format(DecodeFormat.PREFER_ARGB_8888))
                            .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                            .into(itemView.imageViewItemListPlace)
                }
            }
        }

    }

}