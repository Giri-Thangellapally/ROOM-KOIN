package com.jetpack.mvvm_rooom.Model


/*Use Case: Here we are creating the Person data class to insert the data from view model class.*/
/*Rules:
1.Primary constructor must have one parameter value.
2.Data class must be declare either var or val
3.It should not opended,sealed,inner*/


data class Person(
     var name:String,
    var mobileNo:String,
    var photo:String
    )
