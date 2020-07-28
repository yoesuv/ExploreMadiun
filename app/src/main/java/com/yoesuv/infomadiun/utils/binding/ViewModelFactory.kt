package com.yoesuv.infomadiun.utils.binding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.menu.gallery.viewmodels.DetailGalleryViewModel

class ViewModelFactory(private val any: Any): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == DetailGalleryViewModel::class.java) {
            return DetailGalleryViewModel(any as GalleryModel) as T
        }
        return super.create(modelClass)
    }
}