package com.example.dimpguide

import android.provider.ContactsContract

data class User(
    val admin : Boolean = false,
    val username : String,
    val uid: String,
    val year: Int,
    val program : String,
    val email: String,
    val optionalCourseOne: String = "",
    val optionalCourseTwo: String = ""
)