package com.yoesuv.infomadiun.menu.gallery.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
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

    fun getListGallery(){
        compositeDisposable.add(
                restApi.getListGallery()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            listGallery.value = it
                        },{

                        })
        )
    }

    fun destroy(){
        compositeDisposable.clear()
    }

}