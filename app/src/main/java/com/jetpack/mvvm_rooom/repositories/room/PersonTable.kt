package com.jetpack.mvvm_rooom.repositories.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class PersonTable(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    @ColumnInfo(name = "personName")
     val personName: String ,
    @ColumnInfo(name = "personNo")
     val personNo: String,
    @ColumnInfo(name = "personImg")
     val personImg: String
)