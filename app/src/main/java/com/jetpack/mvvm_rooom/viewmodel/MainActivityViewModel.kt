package com.jetpack.mvvm_rooom.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.mvvm_rooom.repositories.MainActivityRepository
import com.jetpack.mvvm_rooom.repositories.room.PersonTable
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(private val repository: MainActivityRepository) : ViewModel() {

    private val TAG = "MainActivityViewModel"
    var personDataList = MutableLiveData<List<PersonTable>>()

    init {
        personDataList.postValue(repository.allPersonsData)
        getAllPersonsData()
    }

    private fun getAllPersonsData(): MutableLiveData<List<PersonTable>> {
        viewModelScope.launch {
                personDataList.postValue(repository.getAllPersonsData())
        }
        return personDataList
    }


    fun insertPersonData(person: PersonTable) {
        if (person.personName.isNotEmpty()) {
            viewModelScope.launch {
                repository.insertPersonData(person)
            }
        }
    }


}