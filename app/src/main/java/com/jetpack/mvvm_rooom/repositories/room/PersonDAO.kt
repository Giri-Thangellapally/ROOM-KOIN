package com.jetpack.mvvm_rooom.repositories.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jetpack.mvvm_rooom.model.Person

@Dao
interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(person: Person)

    @Query("SELECT * FROM persons_table ORDER BY id ASC")
    fun getAllPersonsData(): List<Person>



}