package com.yoesuv.infomadiun.menu.gallery.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.networks.ServiceFactory

class FragmentGalleryViewModel: ViewModel() {

    private var restApi = ServiceFactory.create()

    var listGallery: MutableLiveData<MutableList<GalleryModel>> = MutableLiveData()

    var isLoading: ObservableField<Boolean> = ObservableField()
    var liveDataError: MutableLiveData<Boolean> = MutableLiveData()

    fun getListGallery(){
        isLoading.set(true)
        liveDataError.postValue(false)
    }

}