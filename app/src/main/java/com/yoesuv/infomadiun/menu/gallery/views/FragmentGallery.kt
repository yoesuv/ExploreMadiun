package com.yoesuv.infomadiun.menu.gallery.views

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.FragmentGalleryBinding
import com.yoesuv.infomadiun.menu.gallery.adapters.GalleryAdapter
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.viewmodels.FragmentGalleryViewModel

/**
 *  Updated by yusuf on 14 July 2020
 */
class FragmentGallery: Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var viewModel: FragmentGalleryViewModel

    private lateinit var adapter:GalleryAdapter
    private var listGallery:MutableList<GalleryModel> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(FragmentGalleryViewModel::class.java)

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListGallery()
        viewModel.listGallery.observe(viewLifecycleOwner, Observer { listGallery ->
            onListDataChanged(listGallery!!)
        })
    }

    private fun setupRecyclerView(){
        adapter = GalleryAdapter()
        binding.recyclerViewGallery.adapter = adapter
        binding.recyclerViewGallery.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
    }

    private fun onListDataChanged(listData: MutableList<GalleryModel>){
        listGallery.clear()
        listGallery.addAll(listData)
        binding.recyclerViewGallery.post {
            adapter.notifyDataSetChanged()
        }
    }

}