package com.yusufyildiz.foodbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufyildiz.foodbook.model.Food
import com.yusufyildiz.foodbook.service.FoodDatabase
import kotlinx.coroutines.launch

class DetailsOfFoodViewModel(application: Application) : BaseViewModel(application) {

    var foodLiveData = MutableLiveData<Food>()

    fun getRoomData(uuid : Int)
    {
        launch {

            val dao = FoodDatabase(getApplication()).foodDao()
            val food = dao.getFood(uuid)
            foodLiveData.value = food

        }

    }


}