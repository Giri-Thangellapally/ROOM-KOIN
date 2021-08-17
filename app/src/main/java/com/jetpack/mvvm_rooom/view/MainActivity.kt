package com.jetpack.mvvm_rooom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jetpack.mvvm_rooom.R
import com.jetpack.mvvm_rooom.viewmodel.MainActivityViewModel
import com.jetpack.mvvm_rooom.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity==>"

    private val viewModel by viewModel<MainActivityViewModel>()

    lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Initialize the UI
        initUi()
        viewModel.sayHi()
        //Initialise View Model
//        initViewModelObserver()
    }

    private fun initViewModelObserver() {

        try {

            viewModel.personDataList.observe(this, Observer {

                Log.d(TAG, it.size.toString())

            })

        }catch (exception:Exception){
            Log.d(TAG, exception.message.toString())
        }
    }

    private fun initUi() {
        //Apply scope function will return the context as the object
        dataBinding.apply {

        }
    }

}