package com.jetpack.mvvm_rooom.repositories.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jetpack.mvvm_rooom.model.Person

@Dao
 abstract class  PersonDAO{
    lateinit var allPersonsList: List<Person>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertData(person: Person){}

   @Query("SELECT * FROM person_table")
    fun getAllPersonsData(): List<Person>{
      return  allPersonsList
    }

}