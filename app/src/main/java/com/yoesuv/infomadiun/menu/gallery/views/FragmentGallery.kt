package com.yoesuv.infomadiun.menu.gallery.views

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
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
import kotlinx.android.synthetic.main.fragment_gallery.view.*

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_gallery, container, false)

        val activity:AppCompatActivity = activity as AppCompatActivity
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.menu_gallery)

        setupRecyclerView(v)
        setupSwipeRefresh(v)

        galleryPresenter = GalleryPresenter(this)
        galleryPresenter.getListGallery()

        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        galleryPresenter.destroy()
    }

    private fun setupRecyclerView(view: View?){
        val lManager = GridLayoutManager(view?.context?.applicationContext, 3)
        view?.recyclerViewGallery?.setHasFixedSize(true)
        view?.recyclerViewGallery?.layoutManager = lManager
        adapter = GalleryAdapter(activity as AppCompatActivity, listGallery)
        view?.recyclerViewGallery?.adapter = adapter
        view?.recyclerViewGallery?.itemAnimator = DefaultItemAnimator()
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            view?.recyclerViewGallery?.isNestedScrollingEnabled = true
        }
    }

    private fun setupSwipeRefresh(view: View?){
        view?.swipeRefreshGallery?.setColorSchemeColors(ContextCompat.getColor(view.context, R.color.colorPrimary))
        view?.swipeRefreshGallery?.setOnRefreshListener {
            galleryPresenter.getListGallery()
            view.swipeRefreshGallery.isRefreshing = false
        }
    }

    override fun showLoading() {
        view?.progressBar?.visibility = View.VISIBLE
        view?.progressBar?.alpha = 1f
        view?.recyclerViewGallery?.alpha = 0f
    }

    override fun dismissLoading() {
        view?.recyclerViewGallery?.animate()?.alpha(1f)?.duration = Constants.ANIMATION_TIME
        view?.progressBar?.visibility = View.GONE
    }

    override fun setData(listGallery: MutableList<GalleryModel>) {
        Log.d(Constants.TAG_DEBUG,"FragmentGallery # jumlah gambar gallery ${listGallery.size}")
        this.listGallery.clear()
        if(listGallery.isNotEmpty()){
            this.listGallery.addAll(listGallery)
            view?.recyclerViewGallery?.post({
                adapter.notifyDataSetChanged()
            })
        }
    }

}