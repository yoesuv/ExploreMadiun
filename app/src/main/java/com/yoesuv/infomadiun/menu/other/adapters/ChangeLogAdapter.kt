package com.yoesuv.infomadiun.menu.other.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.yoesuv.infomadiun.menu.other.adapters.viewholders.ChangeLogViewHolder
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel

class ChangeLogAdapter(val context: Context?, var listChangelog: MutableList<ChangeLogModel>): RecyclerView.Adapter<ChangeLogViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangeLogViewHolder {
        return ChangeLogViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return listChangelog.size
    }

    override fun onBindViewHolder(holder: ChangeLogViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.bind(listChangelog[fixPosition])
    }

}