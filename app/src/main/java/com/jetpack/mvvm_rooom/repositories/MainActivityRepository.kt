package com.jetpack.mvvm_rooom.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jetpack.mvvm_rooom.model.Person
import com.jetpack.mvvm_rooom.repositories.room.PersonDAO
import com.jetpack.mvvm_rooom.repositories.room.PersonsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivityRepository(personDAO: PersonDAO){

    private  val TAG = "MainActivityRepository"
    lateinit var allPersonsData: MutableLiveData<List<Person>>


/*
    suspend fun getAllPersonsData() : MutableLiveData<List<Person>> {

        withContext(Dispatchers.IO){
            try {
                allPersonsData.postValue(personDAO.getAllPersonsData())
            }catch (ex:Exception){
                Log.d(TAG, ex.message.toString())
            }
        }
        return allPersonsData
    }
*/

//    suspend fun insertPersonData(person: Person){
//        withContext(Dispatchers.IO){
//            try {
//               personDAO.insertData(person)
//            }catch (ex:Exception){
//                Log.d(TAG, ex.message.toString())
//            }
//        }
//    }

}