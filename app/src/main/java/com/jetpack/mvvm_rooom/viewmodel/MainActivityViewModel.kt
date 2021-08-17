package com.jetpack.mvvm_rooom.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.mvvm_rooom.model.Person
import com.jetpack.mvvm_rooom.repositories.MainActivityRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(private val repository: MainActivityRepository) : ViewModel() {

    private  val TAG = "MainActivityViewModel"
        var personDataList= MutableLiveData<List<Person>>()

    init {
        try {
            personDataList=repository.allPersonsData
            getAllPersonsData()
        }catch (exe:Exception){
            Log.d(TAG, "${exe.message.toString()}")
        }

    }
    fun sayHi():String{
        return "Helooooooooo"
    }

     fun getAllPersonsData(): MutableLiveData<List<Person>> {
        viewModelScope.launch {
            try {
//                personDataList.postValue(repository.getAllPersonsData() as List<Person>)
            }catch (ex:Exception){
                Log.d(TAG, ex.message.toString())
            }
        }
        return personDataList
    }

    suspend fun insertPersonData(person: Person){
        try {
//            repository.insertPersonData(person)
        }catch (ex:Exception){
            Log.d(TAG, ex.message.toString())
        }
    }

    suspend fun deletePersonRecords(mobileNo:Int){


    }
    suspend fun editPersonRecords(person: Person){

    }


}