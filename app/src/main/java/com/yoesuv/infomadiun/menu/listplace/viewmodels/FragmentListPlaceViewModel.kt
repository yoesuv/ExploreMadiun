package com.yoesuv.infomadiun.menu.listplace.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
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

    var isLoading: ObservableField<Boolean> = ObservableField()
    var isError: ObservableField<Boolean> = ObservableField()

    fun getListPlace(){
        isLoading.set(true)
        isError.set(false)
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
                            isError.set(true)
                        })
        )
    }

    fun getListPlaceKabMadiun(){
        isLoading.set(true)
        isError.set(false)
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
                            isError.set(true)
                        })
        )
    }

    fun getListPlaceKabMagetan(){
        isLoading.set(true)
        isError.set(false)
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
                            isError.set(true)
                        })
        )
    }

    fun getListPlaceKabNgawi(){
        isLoading.set(true)
        isError.set(false)
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
                            isError.set(true)
                        })
        )
    }

    fun getListPlaceKabPacitan(){
        isLoading.set(true)
        isError.set(false)
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
                            isError.set(true)
                        })
        )
    }

    fun getListPlaceKabPonorogo(){
        isLoading.set(true)
        isError.set(false)
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
                            isError.set(true)
                        })
        )
    }

    fun getListPlaceKotaMadiun(){
        isLoading.set(true)
        isError.set(false)
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
                            isError.set(true)
                        })
        )
    }

    fun destroy(){
        compositeDisposable.clear()
    }

}