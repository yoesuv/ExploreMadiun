package com.yoesuv.infomadiun.menu.gallery.views

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.FragmentGalleryBinding
import com.yoesuv.infomadiun.menu.gallery.adapters.GalleryAdapter
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.viewmodels.FragmentGalleryViewModel

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentGallery: Fragment() {

    companion object {
        fun getInstance():Fragment{
            return FragmentGallery()
        }
    }

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var viewModel: FragmentGalleryViewModel

    private lateinit var adapter:GalleryAdapter
    private var listGallery:MutableList<GalleryModel> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        viewModel = FragmentGalleryViewModel()

        setupRecyclerView()
        setupSwipeRefresh()

        viewModel.getListGallery()
        viewModel.listGallery.observe(this, Observer {
            onListDataChanged(it!!)
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }

    private fun setupRecyclerView(){
        val lManager = GridLayoutManager(context?.applicationContext, 3)
        binding.recyclerViewGallery?.setHasFixedSize(true)
        binding.recyclerViewGallery?.layoutManager = lManager
        adapter = GalleryAdapter(activity as AppCompatActivity, listGallery)
        binding.recyclerViewGallery?.adapter = adapter
        binding.recyclerViewGallery?.itemAnimator = DefaultItemAnimator()
    }

    private fun setupSwipeRefresh(){
        binding.swipeRefreshGallery?.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorPrimary))
        binding.swipeRefreshGallery?.setOnRefreshListener {
            binding.swipeRefreshGallery.isRefreshing = false
        }
    }

    private fun onListDataChanged(listData: MutableList<GalleryModel>){
        listGallery.clear()
        listGallery.addAll(listData)
        binding.recyclerViewGallery.post {
            adapter.notifyDataSetChanged()
        }
    }

}