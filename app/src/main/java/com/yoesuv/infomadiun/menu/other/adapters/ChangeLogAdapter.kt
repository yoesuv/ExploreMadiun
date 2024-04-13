package com.yoesuv.infomadiun.menu.other.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yoesuv.infomadiun.menu.other.adapters.viewholders.ChangeLogViewHolder
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel
import com.yoesuv.infomadiun.utils.AdapterCallback

class ChangeLogAdapter : ListAdapter<ChangeLogModel, ChangeLogViewHolder>
    (AdapterCallback.diffChangeLogCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangeLogViewHolder {
        return ChangeLogViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ChangeLogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}