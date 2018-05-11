package com.yoesuv.infomadiun.menu.listplace.adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.RelativeLayout
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
        holder.itemView.setOnLongClickListener {
            showPopUpImage(activity, listPlace[fixPos])
            setWindow(activity)
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
        alertDialog.show()
    }

    private fun showPopUpImage(activity: Activity?, model: PlaceModel){
        val intent = Intent(activity, TransparentActivity::class.java)
        intent.putExtra(TransparentActivity.EXTRA_DATA_IMAGE, model.image)
        activity?.startActivity(intent)
    }

    private fun setWindow(activity: Activity?){

        val display = activity?.windowManager?.defaultDisplay
        val point = Point()
        display?.getSize(point)

        val minWidth = point.x * 90 / 100
        val minHeight = point.y * 90/ 100

        Log.d(Constants.TAG_DEBUG,"ListPlaceAdapter # $minWidth/$minHeight")
        activity?.window?.setLayout(minWidth, minHeight)
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