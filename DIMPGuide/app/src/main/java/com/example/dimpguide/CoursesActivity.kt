package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CoursesActivity : AppCompatActivity() {

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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(LoggedInManager.isLoggedIn){
            menuInflater.inflate(R.menu.app_bar_menu,menu)
            return true

        }else
            return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title == getString(R.string.sign_out)){
            LoggedInManager.changeLoginState(false)
            item.title = getString(R.string.sign_in)
            return true
        }else if(item.title == getString(R.string.sign_in)){
            startActivity(Intent(this,SignInActivity::class.java))
            return true
        }
        return false
    }
}
