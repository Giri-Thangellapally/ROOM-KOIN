package com.jetpack.mvvm_rooom.repositories.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jetpack.mvvm_rooom.model.Person

@Dao
 public interface PersonDAO{

  @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertData(person: Person?)

   @Query("SELECT * FROM person_table")
    fun getAllPersonsData(): List<Person>

}