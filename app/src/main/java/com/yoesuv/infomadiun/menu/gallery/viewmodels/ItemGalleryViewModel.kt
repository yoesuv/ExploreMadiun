package com.yoesuv.infomadiun.menu.gallery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel

class ItemGalleryViewModel(galleryModel: GalleryModel) : ViewModel() {
    var imageUrl: ObservableField<String> = ObservableField(galleryModel.thumbnail)
}