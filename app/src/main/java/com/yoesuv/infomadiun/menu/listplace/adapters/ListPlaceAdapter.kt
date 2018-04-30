package com.yoesuv.infomadiun.menu.listplace.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel

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
        holder.setData(listPlace[holder.adapterPosition])
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun setData(placeModel: PlaceModel){
            //itemView.textViewItemListPlaceName.text = placeModel.name
        }

    }

}