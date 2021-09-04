package com.yusufyildiz.foodbook.service

import com.yusufyildiz.foodbook.model.Food
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FoodAPIService {

    //GET, POST
    //https://github.com/atilsamancioglu/BTK20-JSONVeriSeti/blob/master/besinler.json

    //BASE_URL -> https://raw.githubusercontent.com/
    //atilsamancioglu/BTK20-JSONVeriSeti/blob/master/besinler.json

    private val baseURL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(FoodAPI::class.java)

    fun getData() : Single<List<Food>>
    {
        return api.getFood()
    }


}