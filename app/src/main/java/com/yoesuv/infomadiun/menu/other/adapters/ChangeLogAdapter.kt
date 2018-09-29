package com.yoesuv.infomadiun.menu.other.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ItemChangelogBinding
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel
import com.yoesuv.infomadiun.menu.other.viewmodels.ItemChangeLogViewModel

class ChangeLogAdapter(val context: Context?, var listChangelog: MutableList<ChangeLogModel>): RecyclerView.Adapter<ChangeLogAdapter.ChangelogViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangelogViewHolder {
        val binding: ItemChangelogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_changelog, parent, false)
        return ChangelogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listChangelog.size
    }

    override fun onBindViewHolder(holder: ChangelogViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.setupData(listChangelog[fixPosition])
    }

    class ChangelogViewHolder(val binding: ItemChangelogBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setupData(model: ChangeLogModel){
            binding.itemChangelog = ItemChangeLogViewModel(model)
        }

    }
}