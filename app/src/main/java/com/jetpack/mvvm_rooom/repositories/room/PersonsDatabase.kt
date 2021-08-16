package com.jetpack.mvvm_rooom.repositories.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonTable::class], version = 1, exportSchema = false)
abstract class PersonsDatabase : RoomDatabase() {


    abstract fun personDao(): PersonDAO

    companion object {

        @Volatile
        private var DB_INSTANCE: PersonsDatabase? = null

        fun getDataBase(context: Context): PersonsDatabase {

            val temp_db_instance = DB_INSTANCE

            if (temp_db_instance != null) {
                return temp_db_instance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, PersonsDatabase::class.java, "persons_database_v1"
                ).build()
                DB_INSTANCE = instance
                return instance
            }

        }


    }


}