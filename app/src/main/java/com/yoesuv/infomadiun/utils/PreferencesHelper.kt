package com.yoesuv.infomadiun.utils

import android.content.Context
import android.content.SharedPreferences
import com.yoesuv.infomadiun.data.Constants

class PreferencesHelper(context: Context) {

    private val prefHelper: SharedPreferences = context.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setString(name:String, value:String){
        prefHelper.edit().putString(name, value).apply()
    }

    fun getString(name:String): String{
        return prefHelper.getString(name,"")!!
    }
}