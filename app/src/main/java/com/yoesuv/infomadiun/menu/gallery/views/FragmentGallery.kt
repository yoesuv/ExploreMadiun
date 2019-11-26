package com.yoesuv.infomadiun.menu.gallery.views

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
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
        fun getInstance(): Fragment {
            return FragmentGallery()
        }
    }

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var viewModel: FragmentGalleryViewModel

    private lateinit var adapter:GalleryAdapter
    private var listGallery:MutableList<GalleryModel> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        viewModel = ViewModelProviders.of(this).get(FragmentGalleryViewModel::class.java)

        setupRecyclerView()
        setupSwipeRefresh()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListGallery()
        viewModel.listGallery.observe(this, Observer { listGallery ->
            onListDataChanged(listGallery!!)
        })
        viewModel.liveDataError.observe(this, Observer { isError ->
            setupLayoutError(isError)
        })
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