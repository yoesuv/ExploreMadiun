package com.yoesuv.infomadiun.menu.other.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.other.models.LicenseModel
import com.yoesuv.infomadiun.utils.AppHelper
import kotlinx.android.synthetic.main.item_libraries.view.*

class LicenseAdapter(private val context: Context?, private var listLibraries: MutableList<LicenseModel>) : RecyclerView.Adapter<LicenseAdapter.LicenseViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_libraries, parent, false)
        return LicenseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listLibraries.size
    }

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.setupData(listLibraries[fixPosition])
    }


    class LicenseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun setupData(model: LicenseModel){
            itemView.textViewLibraryName.text = model.title
            itemView.textViewLibraryUrl.text = model.url
            itemView.textViewLibraryLicense.text = AppHelper.fromHtml(model.license!!)
            if(model.isLast!!){
                itemView.layoutDivider.visibility = View.GONE
            }
        }

    }
}