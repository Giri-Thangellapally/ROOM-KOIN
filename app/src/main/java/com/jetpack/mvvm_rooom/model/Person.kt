package com.jetpack.mvvm_rooom.model

import androidx.room.Entity


/*Use Case: Here we are creating the Person data class to insert the data from view model class.*/
/*Rules:
1.Primary constructor must have one parameter value.
2.Data class must be declare either var or val
3.It should not opened,sealed,inner*/

data class Person(
     var id:Int,
     var personName:String,
     var personNo:String,
     var personImg:String
    )
