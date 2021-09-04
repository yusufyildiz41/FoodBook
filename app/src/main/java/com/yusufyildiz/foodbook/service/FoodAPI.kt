package com.yusufyildiz.foodbook.service

import android.telecom.Call
import com.yusufyildiz.foodbook.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface FoodAPI {

    //GET, POST
    //https://github.com/atilsamancioglu/BTK20-JSONVeriSeti/blob/master/besinler.json

    //BASE_URL -> https://github.com/
    //atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getFood() : Single<List<Food>> // Call<List<Food>> da kullanılabilir.

}