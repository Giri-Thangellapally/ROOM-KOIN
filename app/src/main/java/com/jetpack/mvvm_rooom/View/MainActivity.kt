package com.jetpack.mvvm_rooom.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jetpack.mvvm_rooom.R
import com.jetpack.mvvm_rooom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Initialize the UI
        initUi()
    }

    private fun initUi() {
        //Apply scope function will return the context as the object
        dataBinding.apply {


        }
    }

}