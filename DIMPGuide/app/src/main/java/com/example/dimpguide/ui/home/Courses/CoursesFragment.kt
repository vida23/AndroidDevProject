package com.example.dimpguide.ui.home.Courses

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dimpguide.*
import com.example.dimpguide.DbHandler.Companion.db
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.lang.Thread.sleep

class CoursesFragment : Fragment() {

    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    companion object {
        fun newInstance() = CoursesFragment()
        var LOOP_VARIABLE = 0
        lateinit var FIRST_COURSE: String
        lateinit var FIRST_COURSE_ID: String
        lateinit var OPTIONAL_COURSE_ONE: String
        lateinit var matchingCourses:String
        const val EMBEDDED_SYSTEMS:String = "Inbyggda system"
        const val SOFTWARE_DEVELOPMENT:String = "Mjukvaruuveckling och mobila plattformar"
        lateinit var optionalCourseOne:String
        lateinit var optionalCourseTwo:String
    }

    fun setOptionalCourses(){
        db.collection("users")
            .whereEqualTo("uid", FirebaseAuth.getInstance().currentUser!!.uid)
            .get()
            .addOnSuccessListener { docs ->
                if (!docs.isEmpty) {
                    for (doc in docs) {
                        if (doc.getString("optionalCourseOne").toString() != null || doc.getString("optionalCourseTwo").toString() != null ) {
                            sleep(300)
                            optionalCourseOne = doc.getString("optionalCourseOne").toString()
                            optionalCourseTwo = doc.getString("optionalCourseTwo").toString()
                        }
                    }
                }

            }
            .addOnFailureListener {
                Log.i("database", "FAIL")
            }
    }

    fun getCourses(){

        val chosenProgram = arguments?.getString("Program")

        val chosenYear = arguments?.getString("Year")
        val dataset: MutableList<StudyPeriod> = ArrayList()
        

        Log.i("database", chosenProgram.toString())

        db.collection("courses")
            .whereIn(
                "pro",
                listOf("dimp", chosenProgram)
            ) //field is either program or dimp
            .whereEqualTo("year", chosenYear)
            .orderBy("period", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->
                // var matchingCourses: String
                for (document in documents) {
                    Log.i("database", document.getString("name").toString())
                    if (document.getLong("period") == 1.toLong()) {

                        if (LOOP_VARIABLE == 0) {
                            FIRST_COURSE = (document.getString("name")).toString()
                            FIRST_COURSE_ID = (document.id)
                            LOOP_VARIABLE += 1
                            continue
                        }

                        matchingCourses = (document.getString("name")).toString()
                        if (FirebaseAuth.getInstance().currentUser != null && document.getString(
                                "year"
                            ) == "3" && optionalCourseOne.length > 1
                        ) {
                            matchingCourses = optionalCourseOne

                            val studyPeriod = StudyPeriod(
                                FIRST_COURSE,
                                matchingCourses,
                                "Period 1",
                                FIRST_COURSE_ID,
                                course2_id = optionalCourseOne,
                                year = document.getString("year").toString()
                            )
                            dataset.add(studyPeriod)
                        }else{
                            val studyPeriod = StudyPeriod(
                                FIRST_COURSE,
                                matchingCourses,
                                "Period 1",
                                FIRST_COURSE_ID,
                                course2_id = document.id,
                                year = document.getString("year").toString()
                            )
                            dataset.add(studyPeriod)
                        }



                        LOOP_VARIABLE = 0

                    } else if (document.getLong("period") == 2.toLong()) {

                        if (LOOP_VARIABLE == 0) {
                            FIRST_COURSE = (document.getString("name")).toString()
                            FIRST_COURSE_ID = (document.id)
                            LOOP_VARIABLE += 1
                            continue
                        }
                        matchingCourses = (document.getString("name")).toString()
                        if (FirebaseAuth.getInstance().currentUser != null && document.getString(
                                "year"
                            ) == "3" && optionalCourseTwo.length > 1
                        ) {
                            matchingCourses = optionalCourseTwo
                            val studyPeriod = StudyPeriod(
                                FIRST_COURSE,
                                matchingCourses,
                                "Period 2",
                                FIRST_COURSE_ID,
                                course2_id = optionalCourseTwo,
                                year = document.getString("year").toString()
                            )
                            dataset.add(studyPeriod)
                        }else{
                            val studyPeriod = StudyPeriod(
                                FIRST_COURSE,
                                matchingCourses,
                                "Period 2",
                                FIRST_COURSE_ID,
                                course2_id = document.id,
                                year = document.getString("year").toString()
                            )
                            dataset.add(studyPeriod)
                        }

                        LOOP_VARIABLE = 0

                    } else if (document.getLong("period") == 3.toLong()) {
                        Log.i("database", document.getString("name"))
                        if (LOOP_VARIABLE == 0) {
                            FIRST_COURSE = (document.getString("name")).toString()
                            FIRST_COURSE_ID = (document.id)
                            LOOP_VARIABLE += 1
                            continue
                        }
                        matchingCourses = (document.getString("name")).toString()

                        val studyPeriod = StudyPeriod(
                            FIRST_COURSE,
                            matchingCourses,
                            "Period 3",
                            FIRST_COURSE_ID,
                            course2_id = document.id,
                            year = document.getString("year").toString()
                        )
                        dataset.add(studyPeriod)

                        LOOP_VARIABLE = 0

                    } else if (document.getLong("period") == 4.toLong()) {
                        Log.i("database", document.getString("name"))
                        if (LOOP_VARIABLE == 0) {
                            FIRST_COURSE = (document.getString("name")).toString()
                            FIRST_COURSE_ID = (document.id)
                            LOOP_VARIABLE += 1
                            continue
                        }

                        matchingCourses = (document.getString("name")).toString()

                        val studyPeriod = StudyPeriod(
                            FIRST_COURSE,
                            matchingCourses,
                            "Period 4",
                            FIRST_COURSE_ID,
                            course2_id = document.id,
                            year = document.getString("year").toString()
                        )
                        dataset.add(studyPeriod)

                        LOOP_VARIABLE = 0
                    }

                }
                viewManager = LinearLayoutManager(context)
                viewAdapter = CourseRecyclerViewAdapter(dataset, context!!)



                coursesRecyclerView =
                    view!!.findViewById<RecyclerView>(R.id.courses_recycler_view).apply {
                        setHasFixedSize(true)

                        layoutManager = viewManager

                        adapter = viewAdapter

                        view!!.findViewById<ProgressBar>(R.id.progress_bar_courses).visibility = View.INVISIBLE
                    }
            }

            .addOnFailureListener { exception ->
                Log.w("Could not find", "Error getting documents: ", exception)
            }
    }
    private lateinit var viewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.courses_fragment, container, false)

        if (FirebaseAuth.getInstance().currentUser != null){
            setOptionalCourses()
            sleep(400)
            getCourses()
            sleep(400)
        }else{
            getCourses()
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CoursesViewModel::class.java)
    }

}
