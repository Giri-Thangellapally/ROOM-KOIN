package com.jetpack.mvvm_rooom.repositories.room

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class PersonTable(
    @PrimaryKey(autoGenerate = true)
    var id : Long=0,
    @ColumnInfo(name = "personName")
     var personName: String,
    @ColumnInfo(name = "personNo")
     var personNo: String,
    @ColumnInfo(name = "personImg")
     var personImg: String,
)