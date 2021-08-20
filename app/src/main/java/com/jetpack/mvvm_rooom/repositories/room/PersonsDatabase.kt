package com.jetpack.mvvm_rooom.repositories.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jetpack.mvvm_rooom.other.ImageConverter

@Database(entities = [PersonTable::class], version = 1, exportSchema = false)
abstract class PersonsDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDAO

}
