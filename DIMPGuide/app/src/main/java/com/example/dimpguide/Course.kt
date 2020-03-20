package com.example.dimpguide

data class Course(
    val course_name: String,
    val course_hp: String,
    val course_period: String,
    val course_preknowledge: String,
    val course_program: String,
    val course_year: String,    //Indicating the year in which the course is taught
    val course_description: String,
    val course_avgRate: Int
)