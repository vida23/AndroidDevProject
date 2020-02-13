package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Specific_course : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_course)
        val name = intent.getStringExtra("name")
        findViewById<TextView>(R.id.Course).apply{
            this.text = name
        }
        val courseMaterialButton = findViewById<Button>(R.id.CourseMaterialButton)

        courseMaterialButton.setOnClickListener {
            val intent = Intent(this,CourseMaterialActivity::class.java)
            //Shall put id from the database here when valid
            startActivity(intent)
        }

    }
}
