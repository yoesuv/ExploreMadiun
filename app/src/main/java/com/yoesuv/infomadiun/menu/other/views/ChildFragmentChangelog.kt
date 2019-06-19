package com.yoesuv.infomadiun.menu.other.views

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ChildFragmentChangelogBinding
import com.yoesuv.infomadiun.menu.other.adapters.ChangeLogAdapter
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel
import com.yoesuv.infomadiun.menu.other.viewmodels.ChildFragmentChangelogViewModel
import com.yoesuv.infomadiun.utils.AppHelper
import kotlinx.android.synthetic.main.child_fragment_changelog.view.*

class ChildFragmentChangelog: androidx.fragment.app.Fragment() {

    companion object {
        fun getInstance(): androidx.fragment.app.Fragment {
            return ChildFragmentChangelog()
        }
    }

    private lateinit var binding: ChildFragmentChangelogBinding
    private lateinit var viewModel: ChildFragmentChangelogViewModel
    private lateinit var adapter: ChangeLogAdapter
    private var listChangelog: MutableList<ChangeLogModel> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.child_fragment_changelog, container, false)
        viewModel = ChildFragmentChangelogViewModel()
        binding.changelog = viewModel

        setupRecycler()
        viewModel.setupData(context)
        viewModel.listData.observe(this, Observer {
            onListDataChanged(it!!)
        })

        return binding.root
    }

    private fun setupRecycler(){
        binding.recyclerViewChangelog.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        adapter = ChangeLogAdapter(context, listChangelog)
        binding.recyclerViewChangelog.adapter = adapter
    }

    private fun onListDataChanged(listData: MutableList<ChangeLogModel>){
        listChangelog.clear()
        listChangelog.addAll(listData)
        binding.recyclerViewChangelog.post {
            adapter.notifyDataSetChanged()
        }
    }

}