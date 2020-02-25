package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CoursesActivity : BaseFunctionsForAllActivities() {

    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        val dataset:Array<Course> = arrayOf(
            Course("Diskret Matematik","Objektorienterad mjukvaruutveckling med designmönster","Augusti"),
            Course("Linjär ALgebra", "Introduktion Till programmejring","Oktober") ,
            Course("Diskret Matematik","Datateknisk Introduktionskurs","Augusti"),
            Course("Linjär ALgebra", "Introduktion Till programmejring","Oktober")
        )

        viewManager = LinearLayoutManager(this)
        viewAdapter = CourseRecyclerViewAdapter(dataset,this)

        coursesRecyclerView = findViewById<RecyclerView>(R.id.coursesRecyclerView).apply{
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }


    }
}
