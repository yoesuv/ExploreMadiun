package com.yoesuv.infomadiun.menu.maps.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import com.yoesuv.infomadiun.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class FragmentMapsViewModel: ViewModel() {

    private var restApi = ServiceFactory.create()
    private val compositeDisposable = CompositeDisposable()

    var listPin: MutableLiveData<MutableList<PinModel>> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    fun getListPin(){
        compositeDisposable.add(
                restApi.getListPin()
                        .timeout(Constants.TIME_OUT, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            listPin.value = it
                        }, {
                            error.value = it
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}