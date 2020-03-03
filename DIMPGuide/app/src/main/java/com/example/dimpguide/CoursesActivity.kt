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
                    if ((document.getString("period")) == "1") {

                        if (LOOP_VARIABLE == 0) {
                            FIRST_COURSE = (document.getString("name")).toString()
                            LOOP_VARIABLE += 1
                            continue
                        }
                        matchingCourses = (document.getString("name")).toString()
                        val studyPeriod = StudyPeriod(FIRST_COURSE, matchingCourses, "Period 1")
                        dataset.add(studyPeriod)
                        Log.d("SP", studyPeriod.course1)
                        Log.d("SP", studyPeriod.course2)

                        LOOP_VARIABLE = 0
                    }
                    else if ((document.getString("period")) == "2") {

                        if (LOOP_VARIABLE == 0) {
                            FIRST_COURSE = (document.getString("name")).toString()
                            LOOP_VARIABLE += 1
                            continue
                        }
                        matchingCourses = (document.getString("name")).toString()
                        val studyPeriod = StudyPeriod(FIRST_COURSE, matchingCourses, "Period 2")
                        dataset.add(studyPeriod)
                        LOOP_VARIABLE = 0
                    }
                    else if ((document.getString("period")) == "3") {

                        if (LOOP_VARIABLE == 0) {
                            FIRST_COURSE = (document.getString("name")).toString()
                            LOOP_VARIABLE += 1
                            continue
                        }
                        matchingCourses = (document.getString("name")).toString()
                        val studyPeriod = StudyPeriod(FIRST_COURSE, matchingCourses, "Period 3")
                        dataset.add(studyPeriod)
                        LOOP_VARIABLE = 0

                    }
                    else ((document.getString("period")) == "4")

                    if(LOOP_VARIABLE == 0){
                        FIRST_COURSE = (document.getString("name")).toString()
                        LOOP_VARIABLE+=1
                        continue
                    }
                    matchingCourses = (document.getString("name")).toString()
                    val studyPeriod = StudyPeriod(FIRST_COURSE, matchingCourses, "Period 4")
                    dataset.add(studyPeriod)
                    LOOP_VARIABLE = 0


                }
                viewManager = LinearLayoutManager(this)
                Log.d("dataset",dataset[0].course1)
                viewAdapter = CourseRecyclerViewAdapter(dataset,this)

                coursesRecyclerView = findViewById<RecyclerView>(R.id.coursesRecyclerView).apply{
                    setHasFixedSize(true)

                    layoutManager = viewManager

                    adapter = viewAdapter
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Could not find", "Error getting documents: ", exception)
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
    companion object {
        var LOOP_VARIABLE = 0
        lateinit var FIRST_COURSE:String
    }
}
