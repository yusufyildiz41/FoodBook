package com.yusufyildiz.foodbook.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufyildiz.foodbook.model.Food
import com.yusufyildiz.foodbook.service.FoodAPIService
import com.yusufyildiz.foodbook.service.FoodDatabase
import com.yusufyildiz.foodbook.util.PrivateSharedPreferencess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application) : BaseViewModel(application) {

    var foods = MutableLiveData<List<Food>>()
    var foodErrorMessage = MutableLiveData<Boolean>()
    var foodLoading = MutableLiveData<Boolean>()

    private var updateTime = (0.2 * 60 * 1000 * 1000 * 1000).toLong()

    private var foodApiService = FoodAPIService()
    private var disposable = CompositeDisposable()
    private var privateSharedPreferences = PrivateSharedPreferencess(getApplication())


    fun refreshData()
    {
        var timeOfSave = privateSharedPreferences.takeTime()
        if(timeOfSave != null && timeOfSave != 0L  && System.nanoTime() - timeOfSave < updateTime)
        {
            // SQLite dan çek
            getDataFromSQLite()

        }
        else
        {
            getDataFromInternet()
        }


    }

    fun refreshFromInternet()
    {
        getDataFromInternet()
    }

    private fun getDataFromSQLite()
    {
        foodLoading.value = true
        //launch içerinden yapmamızın sebebi  suspend fun getAllFood() : List<Food> yapmıştık dao içeriside
        launch {

            val foodList =  FoodDatabase(getApplication()).foodDao().getAllFood()
            showFoods(foodList)
            Toast.makeText(getApplication(),"Besinleri room dan aldık",Toast.LENGTH_LONG).show()

        }


    }

    private fun getDataFromInternet()
    {
        foodLoading.value = true
        //Threads
        //IO, Default, UI,

        disposable.add(

            foodApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>()
                {
                    override fun onSuccess(t: List<Food>) {
                       sqliteUse(t)
                        Toast.makeText(getApplication(),"Besinleri internetten aldık",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {

                        foodErrorMessage.value = true
                        foodLoading.value = false
                        println(e.printStackTrace())

                    }

                })

        )

    }

    private fun showFoods(foodList : List<Food>)
    {
        foods.value = foodList
        foodErrorMessage.value = false
        foodLoading.value = false
    }

    private fun sqliteUse(foodList: List<Food>)
    {
        launch {

            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood()
            val uuidList = dao.insertAll(*foodList.toTypedArray()) // *foodList.toTypedArray() -> besinleri tek tek vericektir(listeyi tekil hale getirir.), vararg istediğim için ve kaç tane geleceğini bilmediğim için onları parçalamam lazım
            var i = 0
            while(i<foodList.size)
            {
                foodList[i].uuid = uuidList[i].toInt()
                i++
            }
            showFoods(foodList)

        }

        privateSharedPreferences.saveTime(System.nanoTime()) // zamanın en güzel birimiyle alınıyor


    }

}