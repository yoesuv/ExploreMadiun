package com.yoesuv.infomadiun.menu.other.views

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ChildFragmentLibrariesBinding
import com.yoesuv.infomadiun.menu.other.adapters.LicenseAdapter
import com.yoesuv.infomadiun.menu.other.models.LicenseModel
import com.yoesuv.infomadiun.menu.other.viewmodels.ChildFragmentLibrariesViewModel

class ChildFragmentLibraries: Fragment() {

    companion object {
        fun getInstance(): Fragment {
            return ChildFragmentLibraries()
        }
    }

    private lateinit var binding: ChildFragmentLibrariesBinding
    private lateinit var viewModel: ChildFragmentLibrariesViewModel
    private var listLibraries: MutableList<LicenseModel> = mutableListOf()
    private lateinit var adapter: LicenseAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.child_fragment_libraries, container, false)
        viewModel = ChildFragmentLibrariesViewModel()
        binding.libraries = viewModel

        setupRecycler()
        viewModel.setupData(context)
        viewModel.listData.observe(viewLifecycleOwner, Observer {
            onListDataChanged(it!!)
        })

        return binding.root
    }

    private fun setupRecycler(){
        binding.recyclerViewLicense.layoutManager = LinearLayoutManager(context)
        adapter = LicenseAdapter(context, listLibraries)
        binding.recyclerViewLicense.adapter = adapter
    }

    private fun onListDataChanged(listData: MutableList<LicenseModel>){
        listLibraries.clear()
        listLibraries.addAll(listData)
        binding.recyclerViewLicense.post {
            adapter.notifyDataSetChanged()
        }
    }

}