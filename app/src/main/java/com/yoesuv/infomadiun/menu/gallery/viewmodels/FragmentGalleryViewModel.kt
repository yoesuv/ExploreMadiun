package com.yoesuv.infomadiun.menu.gallery.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.networks.db.repositories.DbGalleryRepository

class FragmentGalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val dbGallery = DbGalleryRepository(application.applicationContext, viewModelScope)
    var dataGallery: MutableLiveData<List<GalleryModel>> = MutableLiveData()

    init {
        dbGallery.galleries {
            it?.let { galleries ->
                dataGallery.postValue(galleries)
            }
        }
    }

}