package com.yusufyildiz.foodbook.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yusufyildiz.foodbook.model.Food

@Database(entities = arrayOf(Food::class),version = 1)
abstract class FoodDatabase : RoomDatabase(){

    abstract fun foodDao() : FoodDAO

    //Singleton

    companion object {

        @Volatile private var instance : FoodDatabase?=null  //volatile -> farklı threadlere de görünmesini istediğimiz için yazdık

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){ // bu sınıftan bir instance oluşturulduysa onu döndür, oluşturulmadıysa döndürme ve senkronize yap işlemleri.

            instance ?: createMyDatabase(context).also {

                instance = it

            }

        }

        private fun createMyDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            FoodDatabase::class.java,
            "fooddatabase").build()

    }




}