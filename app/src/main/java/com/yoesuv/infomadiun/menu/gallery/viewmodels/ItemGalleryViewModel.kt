package com.yoesuv.infomadiun.menu.gallery.viewmodels

import androidx.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import androidx.databinding.ObservableField
import android.view.View
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.views.DetailGalleryActivity
import java.lang.ref.WeakReference

class ItemGalleryViewModel(private val weakContext: WeakReference<Context>, private val galleryModel: GalleryModel): ViewModel() {
    var imageUrl: ObservableField<String> = ObservableField(galleryModel.thumbnail!!)

    fun onItemClicked(view: View){
        val intent = Intent(weakContext.get(), DetailGalleryActivity::class.java)
        intent.putExtra(DetailGalleryActivity.EXTRA_DATA_GALLERY, galleryModel)
        weakContext.get()?.startActivity(intent)
    }
}