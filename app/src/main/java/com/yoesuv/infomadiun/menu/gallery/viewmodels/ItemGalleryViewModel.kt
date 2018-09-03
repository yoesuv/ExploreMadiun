package com.yoesuv.infomadiun.menu.gallery.viewmodels

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.views.DetailGalleryActivity
import java.lang.ref.WeakReference

class ItemGalleryViewModel(private val weakContext: WeakReference<Context>, private val galleryModel: GalleryModel): ViewModel() {
    var imageUrl: ObservableField<String> = ObservableField(galleryModel.thumbnail!!)

    fun onItemClicked(view: View){
        Log.d(Constants.TAG_DEBUG,"ItemGalleryViewModel # onItemClicked $galleryModel")
        val intent = Intent(weakContext.get(), DetailGalleryActivity::class.java)
        intent.putExtra(DetailGalleryActivity.EXTRA_DATA_GALLERY, galleryModel)
        weakContext.get()?.startActivity(intent)
    }
}