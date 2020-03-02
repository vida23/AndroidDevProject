package com.example.dimpguide
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dimpguide.DbHandler.Companion.db
import kotlin.math.log

class CoursesActivity : AppCompatActivity() {
    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        val dataset:MutableList<StudyPeriod> = ArrayList()
        dataset.add(StudyPeriod("Diskret Matematik","Objektorienterad mjukvaruutveckling med designmönster","Augusti"))
        dataset.add(StudyPeriod("Linjär ALgebra", "Introduktion Till programmejring","Oktober"))
        dataset.add(StudyPeriod("Diskret Matematik","Datateknisk Introduktionskurs","Augusti"))
        dataset.add(StudyPeriod("Linjär ALgebra", "Introduktion Till programmejring","Oktober"))


        /////////////////////////////////////////////////////////////////////////////////////////
/*
        val intent = intent
        val chosenProgram = intent.getStringExtra("Program")
        val chosenYear = intent.getStringExtra("Year")

        val dataset:MutableList<StudyPeriod> = ArrayList()

        db.collection("courses")
            .whereIn("pro", listOf("dimp", chosenProgram)) //field is either program or dimp
            .whereEqualTo("year", chosenYear)
            .get()
            .addOnSuccessListener { documents ->
                var matchingCourses: String
                for (document in documents) {
                    if ((document.getString("period"))=="1") {

                        matchingCourses = (document.getString("name")).toString()
                        dataset.add(StudyPeriod(matchingCourses, matchingCourses, "period 1"))
                        Log.d("look", matchingCourses) //funkar enligt loggen??

                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("didnt find", "Error getting documents: ", exception)
            }

*/
        ////////////////////////////////////////////////////////////////////////////////////////////////////


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
