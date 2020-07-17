package com.yoesuv.infomadiun.menu.gallery.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel

class CustomDetailGalleryViewModelFactory(val application: Application, private val galleryModel: GalleryModel): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailGalleryViewModel(galleryModel, application) as T
    }

}