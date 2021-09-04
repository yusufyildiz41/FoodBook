package com.yusufyildiz.foodbook.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class PrivateSharedPreferencess {

    companion object {

        private var TIME = "time"

        private var sharedPreferences : SharedPreferences?=null

        @Volatile private var instance : PrivateSharedPreferencess?=null

        private val lock = Any()

        operator fun invoke(context : Context) : PrivateSharedPreferencess = instance ?: synchronized(lock){

            instance ?: makePrivateSharedPreferences(context).also {

                instance = it

            }

        }

        private fun makePrivateSharedPreferences(context : Context): PrivateSharedPreferencess {

            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return PrivateSharedPreferencess()

        }

    }

    fun saveTime(time : Long)
    {
        sharedPreferences?.edit(commit = true){
            putLong(TIME,time)
        }
    }
    fun takeTime() : Long? {
        return sharedPreferences?.getLong(TIME,0)
    }
}