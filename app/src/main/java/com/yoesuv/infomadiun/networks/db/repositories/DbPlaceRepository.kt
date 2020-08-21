package com.yoesuv.infomadiun.networks.db.repositories

import android.content.Context
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.networks.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DbPlaceRepository(context: Context, private val scope: CoroutineScope) {

    private val dbPlaces = AppDatabase.getInstance(context)?.placeDaoAccess()

    fun insertPlace(placeModel: PlaceModel) {
        scope.launch {
            dbPlaces?.insertPlace(placeModel)
        }
    }

    fun places(places:(List<PlaceModel>?) -> Unit) {
        scope.launch {
            places(dbPlaces?.places())
        }
    }

    fun placesByLocation(location: String, places:(List<PlaceModel>?) -> Unit) {
        scope.launch {
            places(dbPlaces?.placesByLocation(location))
        }
    }

    fun deleteAllPlace() {
        scope.launch {
            dbPlaces?.deleteAllPlace()
        }
    }

}