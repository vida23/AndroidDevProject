package com.example.dimpguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Specific_course : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_course)
        val name = intent.getStringExtra("name")

    }
}
