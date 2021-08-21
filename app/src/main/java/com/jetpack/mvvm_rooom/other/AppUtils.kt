package com.jetpack.mvvm_rooom.other

import android.graphics.Bitmap
import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation

class AppUtils {

    companion object {

        fun loadPic(imageView: ImageView, bitmap: Bitmap) {
            imageView.load(bitmap)
            {
                transformations(RoundedCornersTransformation(20f))
            }
        }
    }
}