package com.yoesuv.infomadiun.networks.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yoesuv.infomadiun.data.DB_NAME
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.networks.db.dao.PlaceDaoAccess

@Database(entities = [PlaceModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun placeDaoAccess(): PlaceDaoAccess

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = create(context)
            }
            return instance
        }

        private fun create(context: Context): AppDatabase{
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
        }
    }
}