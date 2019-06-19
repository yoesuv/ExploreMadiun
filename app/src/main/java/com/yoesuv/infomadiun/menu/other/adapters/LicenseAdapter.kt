package com.yoesuv.infomadiun.menu.other.adapters

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ItemLibrariesBinding
import com.yoesuv.infomadiun.menu.other.models.LicenseModel
import com.yoesuv.infomadiun.menu.other.viewmodels.ItemLibrariesViewModel

class LicenseAdapter(private val context: Context?, private var listLibraries: MutableList<LicenseModel>) : RecyclerView.Adapter<LicenseAdapter.LicenseViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseViewHolder {
        val binding: ItemLibrariesBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_libraries, parent, false)
        return LicenseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listLibraries.size
    }

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.setupData(listLibraries[fixPosition])
    }


    class LicenseViewHolder(private val binding: ItemLibrariesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setupData(model: LicenseModel){
            binding.itemLibraries = ItemLibrariesViewModel(model)
        }

    }
}