package com.yoesuv.infomadiun.menu.other.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.yoesuv.infomadiun.menu.other.adapters.viewholders.LicenseViewHolder
import com.yoesuv.infomadiun.menu.other.models.LicenseModel

class LicenseAdapter(private var listLibraries: MutableList<LicenseModel>) : RecyclerView.Adapter<LicenseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseViewHolder {
        return LicenseViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return listLibraries.size
    }

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.bind(listLibraries[fixPosition])
    }

}