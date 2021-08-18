package com.jetpack.mvvm_rooom.repositories.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jetpack.mvvm_rooom.model.Person

@Database(entities = [PersonTable::class,Person::class], version = 1, exportSchema = false)
abstract class PersonsDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDAO

}