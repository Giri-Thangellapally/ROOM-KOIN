package com.jetpack.mvvm_rooom.Repositories

import androidx.lifecycle.MutableLiveData
import com.jetpack.mvvm_rooom.Model.Person

class MainActivityRepository {

    lateinit var allPersonsData: MutableLiveData<List<Person>>

    suspend fun getAllPersonsData() :MutableLiveData<List<Person>>{


        return allPersonsData
    }

}