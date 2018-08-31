package com.yoesuv.infomadiun.menu.listplace.adapters

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.*
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ItemListPlaceBinding
import com.yoesuv.infomadiun.main.TransparentActivity
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.listplace.viewmodels.ItemListPlaceViewModel

/**
 *  Created by yusuf on 4/30/18.
 */
class ListPlaceAdapter(private val activity: Activity, private val listPlace:MutableList<PlaceModel>, private val recyclerView: RecyclerView?): RecyclerView.Adapter<ListPlaceAdapter.ViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListPlaceBinding = DataBindingUtil.inflate(inflater, R.layout.item_list_place, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPlace.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fixPos:Int = holder.adapterPosition
        holder.setData(listPlace[fixPos])
        holder.itemView.setOnLongClickListener {
            showPopUpImage(activity, listPlace[fixPos])
            return@setOnLongClickListener true
        }
    }

    private fun showPopUpImage(activity: Activity?, model: PlaceModel){
        recyclerView?.stopScroll()
        val intent = Intent(activity, TransparentActivity::class.java)
        intent.putExtra(TransparentActivity.EXTRA_DATA_IMAGE, model.image)
        activity?.startActivity(intent)
    }

    class ViewHolder(val binding: ItemListPlaceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(placeModel: PlaceModel){
            binding.itemListPlace = ItemListPlaceViewModel(placeModel)
        }

    }

}