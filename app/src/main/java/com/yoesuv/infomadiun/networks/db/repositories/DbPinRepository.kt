package com.yoesuv.infomadiun.networks.db.repositories

import android.content.Context
import android.os.AsyncTask
import com.yoesuv.infomadiun.menu.maps.models.PinModel
import com.yoesuv.infomadiun.networks.db.AppDatabase

class DbPinRepository(context: Context) {

    private val dbPin = AppDatabase.getInstance(context)?.pinDaoAccess()

    fun insertPin(pinModel: PinModel) {
        AsyncTask.execute { dbPin?.insertPin(pinModel) }
    }

    fun pins(pins:(List<PinModel>?) -> Unit) {
        AsyncTask.execute { pins(dbPin?.pins()) }
    }

    fun deleteAllPin() {
        AsyncTask.execute { dbPin?.deleteAllPin() }
    }

}