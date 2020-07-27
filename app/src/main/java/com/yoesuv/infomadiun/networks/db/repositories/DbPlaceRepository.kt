package com.yoesuv.infomadiun.networks.db.repositories

import android.content.Context
import android.os.AsyncTask
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.networks.db.AppDatabase

class DbPlaceRepository(context: Context) {

    private val dbPlaces = AppDatabase.getInstance(context)?.placeDaoAccess()

    fun insertPlace(placeModel: PlaceModel) {
        AsyncTask.execute { dbPlaces?.insertPlace(placeModel) }
    }

    fun deleteAllPlace() {
        AsyncTask.execute { dbPlaces?.deleteAllPlace() }
    }

}