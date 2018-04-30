package com.yoesuv.infomadiun.menu.listplace.presenters

import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.listplace.contracts.ListPlaceContract
import com.yoesuv.infomadiun.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *  Created by yusuf on 4/30/18.
 */
class ListPlacePresenter(val viewListPlace: ListPlaceContract.ViewListPlace): ListPlaceContract.Presenter {

    private var restApi = ServiceFactory.create()
    private val compositeDisposable = CompositeDisposable()

    override fun getListPlace() {
        viewListPlace.showLoading()
        compositeDisposable.add(
                restApi.getListPlace()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                                onNext = {
                                    viewListPlace.setData(it)
                                },
                                onError = {
                                    viewListPlace.dismissLoading()
                                },
                                onComplete = {
                                    viewListPlace.dismissLoading()
                                }
                        )
        )
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}