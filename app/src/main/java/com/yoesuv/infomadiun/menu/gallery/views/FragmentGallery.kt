package com.yoesuv.infomadiun.menu.gallery.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.gallery.adapters.GalleryAdapter
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.contracts.GalleryContract
import com.yoesuv.infomadiun.menu.gallery.presenters.GalleryPresenter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_gallery.*

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentGallery: Fragment(), GalleryContract.ViewGallery {

    companion object {
        fun getInstance():Fragment{
            return FragmentGallery()
        }
    }

    private lateinit var galleryPresenter: GalleryPresenter
    private lateinit var adapter:GalleryAdapter
    private var listGallery:MutableList<GalleryModel> = arrayListOf()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_gallery, container, false)

        val activity:AppCompatActivity = activity as AppCompatActivity
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.menu_gallery)

        setupRecyclerView(v)

        galleryPresenter = GalleryPresenter(this)
        galleryPresenter.getListGallery()

        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        galleryPresenter.destroy()
    }

    private fun setupRecyclerView(view: View){
        recyclerView = view.findViewById(R.id.recyclerViewGallery)
        val lManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        lManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = lManager
        adapter = GalleryAdapter(activity as AppCompatActivity, listGallery)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun setData(listGallery: MutableList<GalleryModel>) {
        Log.d(Constants.TAG_DEBUG,"FragmentGallery # jumlah gambar gallery ${listGallery.size}")
        this.listGallery.clear()
        if(listGallery.isNotEmpty()){
            this.listGallery.addAll(listGallery)
            recyclerView.post({
                adapter.notifyDataSetChanged()
            })
        }
    }

}