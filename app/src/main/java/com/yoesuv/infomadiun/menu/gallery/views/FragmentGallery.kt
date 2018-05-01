package com.yoesuv.infomadiun.menu.gallery.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.contracts.GalleryContract
import com.yoesuv.infomadiun.menu.gallery.presenters.GalleryPresenter
import kotlinx.android.synthetic.main.activity_main.view.*

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_gallery, container, false)

        val activity:AppCompatActivity = activity as AppCompatActivity
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.menu_gallery)

        galleryPresenter = GalleryPresenter(this)
        galleryPresenter.getListGallery()

        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        galleryPresenter.destroy()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun setData(listGallery: MutableList<GalleryModel>) {
        Log.d(Constants.TAG_DEBUG,"FragmentGallery # jumlah gambar gallery ${listGallery.size}")
    }

}