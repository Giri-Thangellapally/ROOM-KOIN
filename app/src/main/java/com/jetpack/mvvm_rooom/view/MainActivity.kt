package com.jetpack.mvvm_rooom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.jetpack.mvvm_rooom.R
import com.jetpack.mvvm_rooom.viewmodel.MainActivityViewModel
import com.jetpack.mvvm_rooom.databinding.ActivityMainBinding
import com.jetpack.mvvm_rooom.repositories.room.PersonTable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    //Initialized the ViewModel from koin(lazy) dependency injection.
    private val viewModel by viewModel<MainActivityViewModel>()

    //data binding to avoid the FindViewById .
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //initialize the Views
        initViews()
        //Initialize ViewModel observer
        initViewModelObserver()
    }

    private fun initViews() {
        dataBinding.apply {
            btnSubmit.setOnClickListener {
                viewModel.insertPersonData(
                    PersonTable(
                        personName = etPersonName.text.toString(),
                        personNo = etPersonMobileNo.text.toString()
                    )
                )
            }
        }
    }

    private fun initViewModelObserver() {
        viewModel.personDataList.observe(this, {

        })
    }
}