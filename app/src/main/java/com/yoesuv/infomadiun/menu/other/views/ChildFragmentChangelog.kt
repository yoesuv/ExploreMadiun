package com.yoesuv.infomadiun.menu.other.views

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.other.adapters.ChangeLogAdapter
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel
import com.yoesuv.infomadiun.utils.AppHelper
import kotlinx.android.synthetic.main.child_fragment_changelog.view.*

class ChildFragmentChangelog: Fragment() {

    companion object {
        fun getInstance():Fragment{
            return ChildFragmentChangelog()
        }
    }

    private var listChangelog: MutableList<ChangeLogModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listChangelog.add(ChangeLogModel(getString(R.string.ver_9), getString(R.string.ver_9_info), false))
        listChangelog.add(ChangeLogModel(getString(R.string.ver_8), getString(R.string.ver_8_info), false))
        listChangelog.add(ChangeLogModel(getString(R.string.ver_7), getString(R.string.ver_7_info), false))
        listChangelog.add(ChangeLogModel(getString(R.string.ver_6), getString(R.string.ver_6_info), false))
        listChangelog.add(ChangeLogModel(getString(R.string.ver_5), getString(R.string.ver_5_info), false))
        listChangelog.add(ChangeLogModel(getString(R.string.ver_4), getString(R.string.ver_4_info), false))
        listChangelog.add(ChangeLogModel(getString(R.string.ver_3), getString(R.string.ver_3_info), false))
        listChangelog.add(ChangeLogModel(getString(R.string.ver_2), getString(R.string.ver_2_info), false))
        listChangelog.add(ChangeLogModel(getString(R.string.ver_1), getString(R.string.ver_1_info), true))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.child_fragment_changelog, container, false)

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            view.recyclerViewChangelog.isNestedScrollingEnabled = true
        }

        view.recyclerViewChangelog.layoutManager = LinearLayoutManager(context)
        val adapter = ChangeLogAdapter(context, listChangelog)
        view.recyclerViewChangelog.adapter = adapter

        return view
    }

}