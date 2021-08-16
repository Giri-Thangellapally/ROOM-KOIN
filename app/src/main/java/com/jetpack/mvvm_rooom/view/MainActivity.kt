package com.jetpack.mvvm_rooom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jetpack.mvvm_rooom.R
import com.jetpack.mvvm_rooom.viewmodel.MainActivityViewModel
import com.jetpack.mvvm_rooom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityMainBinding
    lateinit var viewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Initialize the UI
        initUi()
        //Initialise View Model
        initViewModelObserver()
    }

    private fun initViewModelObserver() {
        viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.personDataList.observe(this, Observer {
        })
    }

    private fun initUi() {
        //Apply scope function will return the context as the object
        dataBinding.apply {
        }
    }

}