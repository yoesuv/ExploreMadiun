package com.yoesuv.infomadiun.menu.gallery.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.gallery.models.GalleryModel
import com.yoesuv.infomadiun.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class FragmentGalleryViewModel: ViewModel() {

    private var restApi = ServiceFactory.create()
    private val compositeDisposable = CompositeDisposable()

    var listGallery: MutableLiveData<MutableList<GalleryModel>> = MutableLiveData()

    var isLoading: ObservableField<Boolean> = ObservableField()
    var liveDataError: MutableLiveData<Boolean> = MutableLiveData()

    fun getListGallery(){
        isLoading.set(true)
        liveDataError.postValue(false)
        compositeDisposable.add(
                restApi.getListGallery()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            isLoading.set(false)
                            listGallery.value = it
                        },{
                            isLoading.set(false)
                            liveDataError.postValue(true)
                        })
        )
    }

    fun destroy(){
        compositeDisposable.clear()
    }

}