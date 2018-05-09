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
class ListPlacePresenter(private val viewListPlace: ListPlaceContract.ViewListPlace): ListPlaceContract.Presenter {

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

    override fun getListPlaceKabMadiun() {
        viewListPlace.showLoading()
        compositeDisposable.add(
                restApi.getListPlaceKabMadiun()
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

    override fun getListPlaceKabMagetan() {
        viewListPlace.showLoading()
        compositeDisposable.add(
                restApi.getListPlaceKabMagetan()
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

    override fun getListPlaceKabNgawi() {
        viewListPlace.showLoading()
        compositeDisposable.add(
                restApi.getListPlaceKabNgawi()
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

    override fun getListPlaceKabPacitan() {
        viewListPlace.showLoading()
        compositeDisposable.add(
                restApi.getListPlaceKabPacitan()
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

    override fun getListPlaceKabPonorogo() {
        viewListPlace.showLoading()
        compositeDisposable.add(
                restApi.getListPlaceKabPonorogo()
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

    override fun getListPlaceKotaMadiun() {
        viewListPlace.showLoading()
        compositeDisposable.add(
                restApi.getListPlaceKotaMadiun()
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