package com.yoesuv.infomadiun.menu.gallery.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel

class ItemGalleryViewModel(
    galleryModel: GalleryModel,
) : ViewModel() {
    var imageUrl: ObservableField<String> = ObservableField(galleryModel.thumbnail)
}
