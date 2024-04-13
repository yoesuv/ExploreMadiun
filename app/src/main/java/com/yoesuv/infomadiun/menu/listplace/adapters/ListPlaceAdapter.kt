package com.yoesuv.infomadiun.menu.listplace.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yoesuv.infomadiun.menu.listplace.adapters.viewholders.PlaceViewHolder
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.utils.AdapterCallback

/**
 *  Updated by yusuf on 26 July 2020.
 */
class ListPlaceAdapter(val onItemClick: (PlaceModel) -> Unit) : ListAdapter<PlaceModel, PlaceViewHolder>(AdapterCallback.diffPlaceCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val placeModel = getItem(position)
        holder.bind(placeModel)
        holder.itemView.setOnClickListener {
            onItemClick(placeModel)
        }
    }

}