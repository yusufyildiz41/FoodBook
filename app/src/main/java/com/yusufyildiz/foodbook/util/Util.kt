package com.yusufyildiz.foodbook.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yusufyildiz.foodbook.R

/*
fun String.myExtensions(parameter : String)
{
    println(parameter)
}
 */

fun ImageView.downloadImage(url : String?,placeholder : CircularProgressDrawable)
{
    var options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher)

    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}
fun makePlaceHolder(context : Context) : CircularProgressDrawable
{
    return CircularProgressDrawable(context).apply {

        strokeWidth = 8f
        centerRadius = 40f
        start()

    }
}

@BindingAdapter("android:downloadImageForXML") // xml de databinding kullanıyoruz resmi almak için
fun downloadImageForXML(imageView: ImageView,url : String?)
{
    imageView.downloadImage(url, makePlaceHolder(imageView.context))

}