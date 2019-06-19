package com.yoesuv.infomadiun.menu.gallery.views

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.databinding.FragmentGalleryBinding
import com.yoesuv.infomadiun.menu.gallery.adapters.GalleryAdapter
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.viewmodels.FragmentGalleryViewModel

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentGallery: androidx.fragment.app.Fragment() {

    companion object {
        fun getInstance(): androidx.fragment.app.Fragment {
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
        viewModel.listGallery.observe(this, Observer { listGallery ->
            onListDataChanged(listGallery!!)
        })
        viewModel.liveDataError.observe(this, Observer { isError ->
            setupLayoutError(isError)
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }

    private fun setupRecyclerView(){
        val lManager = androidx.recyclerview.widget.GridLayoutManager(context?.applicationContext, 3)
        binding.recyclerViewGallery.setHasFixedSize(true)
        binding.recyclerViewGallery.layoutManager = lManager
        adapter = GalleryAdapter(activity as AppCompatActivity, listGallery)
        binding.recyclerViewGallery.adapter = adapter
        binding.recyclerViewGallery.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
    }

    private fun setupSwipeRefresh(){
        binding.swipeRefreshGallery.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorPrimary))
        binding.swipeRefreshGallery.setOnRefreshListener {
            binding.swipeRefreshGallery.isRefreshing = false
            viewModel.getListGallery()
        }
    }

    private fun onListDataChanged(listData: MutableList<GalleryModel>){
        listGallery.clear()
        listGallery.addAll(listData)
        binding.recyclerViewGallery.post {
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupLayoutError(isError: Boolean?){
        if (isError!!) {
            binding.layoutGalleryError.visibility = View.VISIBLE
            binding.layoutGalleryError.findViewById<AppCompatButton>(R.id.buttonErrorRefresh).setOnClickListener {
                viewModel.getListGallery()
            }
        } else {
            binding.layoutGalleryError.visibility = View.GONE
        }
    }

}