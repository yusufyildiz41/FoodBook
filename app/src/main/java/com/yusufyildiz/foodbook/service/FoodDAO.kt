package com.yusufyildiz.foodbook.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yusufyildiz.foodbook.model.Food
import retrofit2.http.DELETE

@Dao
interface FoodDAO {

    //Data Access Object (DAO)

    @Insert
    suspend fun insertAll(vararg food : Food) : List<Long>

    //Insert -> Room, insert into
    //Suspend -> coroutine scope
    //vararg -> birden fazla ve istediğimiz sayıda besin
    //List<Long> -> long , id'ler

    @Query("SELECT * FROM food")
    suspend fun getAllFood() : List<Food>

    @Query("SELECT * FROM food WHERE uuid = :foodId")
    suspend fun getFood(foodId : Int) : Food

    @Query("DELETE FROM food")
    suspend fun deleteAllFood()

}