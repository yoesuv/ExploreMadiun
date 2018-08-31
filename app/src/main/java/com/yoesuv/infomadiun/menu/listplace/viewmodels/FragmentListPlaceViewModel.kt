package com.yoesuv.infomadiun.menu.listplace.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class FragmentListPlaceViewModel: ViewModel() {

    private val restApi = ServiceFactory.create()
    private val compositeDisposable = CompositeDisposable()

    var listPlace: MutableLiveData<MutableList<PlaceModel>> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    var isLoading: ObservableField<Boolean> = ObservableField()

    fun getListPlace(){
        isLoading.set(true)
        compositeDisposable.add(
                restApi.getListPlace()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isLoading.set(false)
                            listPlace.value = it
                        },{
                            isLoading.set(false)
                            error.value = it
                        })
        )
    }

    fun getListPlaceKabMadiun(){
        isLoading.set(true)
        compositeDisposable.add(
                restApi.getListPlaceKabMadiun()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isLoading.set(false)
                            listPlace.value = it
                        },{
                            isLoading.set(false)
                            error.value = it
                        })
        )
    }

    fun getListPlaceKabMagetan(){
        isLoading.set(true)
        compositeDisposable.add(
                restApi.getListPlaceKabMagetan()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isLoading.set(false)
                            listPlace.value = it
                        },{
                            isLoading.set(false)
                            error.value = it
                        })
        )
    }

    fun getListPlaceKabNgawi(){
        isLoading.set(true)
        compositeDisposable.add(
                restApi.getListPlaceKabNgawi()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isLoading.set(false)
                            listPlace.value = it
                        },{
                            isLoading.set(false)
                            error.value = it
                        })
        )
    }

    fun getListPlaceKabPacitan(){
        isLoading.set(true)
        compositeDisposable.add(
                restApi.getListPlaceKabPacitan()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isLoading.set(false)
                            listPlace.value = it
                        },{
                            isLoading.set(false)
                            error.value = it
                        })
        )
    }

    fun getListPlaceKabPonorogo(){
        isLoading.set(true)
        compositeDisposable.add(
                restApi.getListPlaceKabPonorogo()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isLoading.set(false)
                            listPlace.value = it
                        },{
                            isLoading.set(false)
                            error.value = it
                        })
        )
    }

    fun getListPlaceKotaMadiun(){
        isLoading.set(true)
        compositeDisposable.add(
                restApi.getListPlaceKotaMadiun()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isLoading.set(false)
                            listPlace.value = it
                        },{
                            isLoading.set(false)
                            error.value = it
                        })
        )
    }

    fun destroy(){
        compositeDisposable.clear()
    }

}