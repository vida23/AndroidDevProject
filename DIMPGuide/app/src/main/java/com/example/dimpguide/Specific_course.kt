package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.example.dimpguide.DbHandler.Companion.db

class Specific_course : BaseFunctionsForAllActivities() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_course)
        val name = intent.getStringExtra("name")
        val course_id = intent.getStringExtra("course_id")
        findViewById<TextView>(R.id.Course).apply{
            this.text = name
        }


        findViewById<Button>(R.id.CourseMaterialButton)
            .setOnClickListener {
                val intent = Intent(this,CourseMaterialActivity::class.java)
                //Shall put id from the database here when valid
                startActivity(intent)
            }

        findViewById<Button>(R.id.FAQButton)
            .setOnClickListener {
                startActivity(Intent(this,FAQActivity::class.java))
            }

        findViewById<Button>(R.id.AskButton)
            .setOnClickListener {
                startActivity(Intent(this,AskActivity::class.java))
            }

        findViewById<Button>(R.id.GoodToKnowButton)
            .setOnClickListener {
                startActivity(Intent(this,GoodToKnowActivity::class.java).apply {
                    putExtra("name", name);
                    putExtra("course_id", course_id)
                })
            }
    }
}
