package com.yoesuv.infomadiun.menu.gallery.presenters

import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.gallery.contracts.GalleryContract
import com.yoesuv.infomadiun.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *  Created by yusuf on 5/1/18.
 */
class GalleryPresenter(private val viewGallery: GalleryContract.ViewGallery): GalleryContract.Presenter {

    private var restApi = ServiceFactory.create()
    private val compositeDisposable = CompositeDisposable()

    override fun getListGallery() {
        viewGallery.showLoading()
        compositeDisposable.add(
                restApi.getListGallery()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                                onNext = {
                                    viewGallery.setData(it)
                                },
                                onError = {
                                    viewGallery.dismissLoading()
                                    viewGallery.setError()
                                },
                                onComplete = {
                                    viewGallery.dismissLoading()
                                }
                        )
        )
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}