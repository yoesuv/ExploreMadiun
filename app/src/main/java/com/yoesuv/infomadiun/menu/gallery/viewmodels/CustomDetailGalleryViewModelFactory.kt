package com.yoesuv.infomadiun.menu.gallery.viewmodels

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel

class CustomDetailGalleryViewModelFactory(val galleryModel: GalleryModel, val application: Application): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailGalleryViewModel(galleryModel, application) as T
    }

}