package com.jetpack.mvvm_rooom.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.mvvm_rooom.Model.Person
import com.jetpack.mvvm_rooom.Repositories.MainActivityRepository
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    var personDataList: MutableLiveData<List<Person>>
    lateinit var repository: MainActivityRepository

    //init block will call immediately once after the class creation
    init {
        personDataList = repository.allPersonsData
        getAllPersonsData()
    }

     fun getAllPersonsData(): MutableLiveData<List<Person>> {
        viewModelScope.launch {
            personDataList.postValue(repository.getAllPersonsData() as List<Person>)
        }
        return personDataList
    }

    suspend fun insertPersonData(person: Person){


    }

    suspend fun deletePersonRecords(mobileNo:Int){

    }
    suspend fun editPersonRecords(person: Person){

    }


}