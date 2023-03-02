package com.yoesuv.infomadiun.menu.other.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yoesuv.infomadiun.menu.other.adapters.viewholders.LicenseViewHolder
import com.yoesuv.infomadiun.menu.other.models.LicenseModel
import com.yoesuv.infomadiun.utils.AdapterCallback

class LicenseAdapter : ListAdapter<LicenseModel, LicenseViewHolder>
    (AdapterCallback.diffLicenseCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseViewHolder {
        return LicenseViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}