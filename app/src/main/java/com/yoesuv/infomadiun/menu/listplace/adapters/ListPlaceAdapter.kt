package com.yoesuv.infomadiun.menu.listplace.adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.yoesuv.infomadiun.BuildConfig
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.widgets.ToolbarTextView
import kotlinx.android.synthetic.main.item_list_place.view.*
import kotlinx.android.synthetic.main.popup_detail_list_place.*
import kotlinx.android.synthetic.main.popup_detail_list_place.view.*

/**
 *  Created by yusuf on 4/30/18.
 */
class ListPlaceAdapter(private val activity: Activity, private val listPlace:MutableList<PlaceModel>): RecyclerView.Adapter<ListPlaceAdapter.ViewHolder>() {

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
        holder.itemView.setOnClickListener({
            showPopUp(activity, listPlace[fixPos])
        })
    }

    private fun showPopUp(activity: Activity?, model: PlaceModel){
        val alertDialog:AlertDialog
        val ab:AlertDialog.Builder = AlertDialog.Builder(activity)
        val view:View = LayoutInflater.from(activity?.applicationContext).inflate(R.layout.popup_detail_list_place, null)
        ab.setView(view)
        view.textViewPopUpListPlaceName.text = model.name
        view.textViewPopUpListPlaceDescription.text = model.description
        view.imageViewPopupListPlace.scaleType = ImageView.ScaleType.CENTER_CROP
        alertDialog = ab.create()
        alertDialog.show()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val context:Context? = itemView?.context?.applicationContext

        fun setData(placeModel: PlaceModel){
            itemView.textViewItemListPlaceName.text = placeModel.name
            itemView.textViewItemListPlaceLocation.text = placeModel.location
            itemView.imageViewItemListPlace.scaleType = ImageView.ScaleType.CENTER_CROP
            Glide.with(context)
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