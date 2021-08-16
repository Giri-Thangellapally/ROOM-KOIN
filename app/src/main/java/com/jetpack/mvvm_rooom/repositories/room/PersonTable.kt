package com.jetpack.mvvm_rooom.repositories.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons_table")
data class PersonTable(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
     val personName: String = "",
     val PersonNo: String = "",
     val PersonImg: String = ""
)