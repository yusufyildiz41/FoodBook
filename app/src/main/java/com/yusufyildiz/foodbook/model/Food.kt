package com.yusufyildiz.foodbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Food(

    @ColumnInfo(name="isim")
    @SerializedName("isim")
    var name : String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    var calorie : String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    var carbohydrate : String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    var protein : String?,
    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    var oil : String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    var image : String?
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}