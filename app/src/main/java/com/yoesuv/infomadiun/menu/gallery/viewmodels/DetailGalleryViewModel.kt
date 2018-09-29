package com.yoesuv.infomadiun.menu.gallery.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel

class DetailGalleryViewModel(galleryModel: GalleryModel, application: Application) : AndroidViewModel(application) {

    var imageUrl: ObservableField<String> = ObservableField(galleryModel.image!!)
    var caption: ObservableField<String> = ObservableField(galleryModel.caption!!)
}