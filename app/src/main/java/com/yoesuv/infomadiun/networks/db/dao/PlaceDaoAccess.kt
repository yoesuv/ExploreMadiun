package com.yoesuv.infomadiun.networks.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel

@Dao
interface PlaceDaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(placeModel: PlaceModel)

    @Query("SELECT * FROM PLACES")
    suspend fun places(): List<PlaceModel>

    @Query("SELECT * FROM places WHERE location =:location")
    suspend fun placesByLocation(location: String): List<PlaceModel>

    @Query("DELETE FROM places")
    suspend fun deleteAllPlace()
}