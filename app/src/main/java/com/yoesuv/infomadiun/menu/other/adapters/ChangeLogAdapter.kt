package com.yoesuv.infomadiun.menu.other.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel
import com.yoesuv.infomadiun.utils.AppHelper
import kotlinx.android.synthetic.main.item_changelog.view.*

class ChangeLogAdapter(val context: Context?, var listChangelog: MutableList<ChangeLogModel>): RecyclerView.Adapter<ChangeLogAdapter.ChangelogViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangelogViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_changelog, parent, false)
        return ChangelogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listChangelog.size
    }

    override fun onBindViewHolder(holder: ChangelogViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.setupData(listChangelog[fixPosition])
    }

    class ChangelogViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun setupData(model: ChangeLogModel){
            itemView.textViewChangelogTitle.text = model.title
            itemView.textViewChangelogDescription.text = AppHelper.fromHtml(model.description!!)
            if(model.isLast!!){
                itemView.layoutDivider.visibility = View.GONE
            }
        }

    }
}