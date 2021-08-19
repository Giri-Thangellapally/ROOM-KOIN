package com.jetpack.mvvm_rooom.repositories

import androidx.lifecycle.MutableLiveData
import com.jetpack.mvvm_rooom.repositories.room.PersonDAO
import com.jetpack.mvvm_rooom.repositories.room.PersonTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
repository class where we decide to get the data either from database or network class
*/

class MainActivityRepository(private val personDAO: PersonDAO){

     var allPersonsData= listOf<PersonTable>()

    /*get the all persons data from the database*/
    suspend fun getAllPersonsData() : List<PersonTable> {
        withContext(Dispatchers.IO){
                allPersonsData=personDAO.getAllPersonsData()
        }
        return allPersonsData
    }

    /*insert the person data into the database*/
    suspend fun insertPersonData(person: PersonTable){
        val res:Long
        withContext(Dispatchers.IO){
        personDAO.insertData(person)
        }
    }

}