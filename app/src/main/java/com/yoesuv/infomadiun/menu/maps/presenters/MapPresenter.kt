package com.yoesuv.infomadiun.menu.maps.presenters

import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.maps.contracts.MapContract
import com.yoesuv.infomadiun.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MapPresenter(private val viewMaps: MapContract.ViewMaps): MapContract.Presenter {

    private var restApi = ServiceFactory.create()
    private val compositeDisposable = CompositeDisposable()

    override fun getListPin() {
        viewMaps.showLoading()
        compositeDisposable.add(
                restApi.getListPin()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                            onNext = {
                                viewMaps.setData(it)
                            },
                            onError = {
                                viewMaps.dismissLoading()
                            },
                            onComplete = {
                                viewMaps.dismissLoading()
                            }
                    )
        )
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}