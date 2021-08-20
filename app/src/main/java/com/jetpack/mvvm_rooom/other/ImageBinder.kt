package com.jetpack.mvvm_rooom.other

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import java.io.File

@BindingAdapter("imgPath")
fun bindImage(imageView: ImageView,imgPath:String){
    imageView.load(File(imgPath))
}