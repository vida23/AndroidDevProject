package com.example.dimpguide

data class User(
    val admin : Boolean = false,
    val username : String,
    val password: String,
    val year: Int,
    val program : String
)