package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.dimpguide.DbHandler.Companion.db
import kotlinx.android.synthetic.main.activity_goodtoknow.*

class GoodToKnowActivity : BaseFunctionsForAllActivities() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goodtoknow)

        val intent = intent
        val selectedCourse = intent.getStringExtra("name")
        val course_id = intent.getStringExtra("course_id")
        goodToKnowCourseNameTextView.apply {
            this.text = selectedCourse
        }


        val docRef = db.collection("courses").document(course_id)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null){
                    findViewById<TextView>(R.id.goodToKnowTextView).apply {
                        this.text = document.get("pre").toString()
                    }

                    Log.d("docs", document.get("pre").toString())
                }
                else{
                    Log.d("docs", "Cant find document")
                }
            }
            .addOnFailureListener{ exception ->
                Log.d("docs", "failed to fetch data", exception)
            }
    }
}
