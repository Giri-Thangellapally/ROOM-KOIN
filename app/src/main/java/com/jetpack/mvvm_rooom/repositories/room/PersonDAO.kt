package com.jetpack.mvvm_rooom.repositories.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
 interface PersonDAO{

  @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertData(person: PersonTable): Long

   @Query("SELECT * FROM person_table ORDER BY id DESC")
    fun getAllPersonsData(): List<PersonTable>

}