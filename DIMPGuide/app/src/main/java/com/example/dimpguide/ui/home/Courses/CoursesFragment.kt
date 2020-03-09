package com.example.dimpguide.ui.home.Courses

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dimpguide.*
import com.google.firebase.firestore.Query

class CoursesFragment : Fragment() {

    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    companion object {
        fun newInstance() = CoursesFragment()
    }

    private lateinit var viewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.courses_fragment, container, false)


        val intent = activity?.intent
        var chosenProgram = intent?.getStringExtra("Program")

        Log.i("CourseFragment", chosenProgram.toString())
        val chosenYear = intent?.getStringExtra("Year")
        val dataset: MutableList<StudyPeriod> = ArrayList()

        if (chosenProgram == "Software Development") {
            chosenProgram = "Mjukvaruutveckling och mobila plattformar"
        } else if (chosenProgram == "Embedded Systems") {
            chosenProgram = "Inbyggda system"
        }


        DbHandler.db.collection("courses")
            .whereIn("pro", listOf("dimp", chosenProgram)) //field is either program or dimp
            .whereEqualTo("year", chosenYear)
            .orderBy("period", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->
                var matchingCourses: String
                for (document in documents) {
                    if (document.getLong("period") == 1.toLong()) {

                        if (CoursesActivity.LOOP_VARIABLE == 0) {
                            CoursesActivity.FIRST_COURSE = (document.getString("name")).toString()
                            CoursesActivity.FIRST_COURSE_ID = (document.id)
                            CoursesActivity.LOOP_VARIABLE += 1
                            continue
                        }
                        matchingCourses = (document.getString("name")).toString()
                        val studyPeriod = StudyPeriod(
                            CoursesActivity.FIRST_COURSE,
                            matchingCourses,
                            "Period 1",
                            CoursesActivity.FIRST_COURSE_ID,
                            course2_id = document.id
                        )
                        dataset.add(studyPeriod)

                        CoursesActivity.LOOP_VARIABLE = 0

                    } else if (document.getLong("period") == 2.toLong()) {

                        if (CoursesActivity.LOOP_VARIABLE == 0) {
                            CoursesActivity.FIRST_COURSE = (document.getString("name")).toString()
                            CoursesActivity.FIRST_COURSE_ID = (document.id)
                            CoursesActivity.LOOP_VARIABLE += 1
                            continue
                        }
                        matchingCourses = (document.getString("name")).toString()
                        val studyPeriod = StudyPeriod(
                            CoursesActivity.FIRST_COURSE,
                            matchingCourses,
                            "Period 2",
                            CoursesActivity.FIRST_COURSE_ID,
                            course2_id = document.id
                        )
                        dataset.add(studyPeriod)

                        CoursesActivity.LOOP_VARIABLE = 0

                    } else if (document.getLong("period") == 3.toLong()) {

                        if (CoursesActivity.LOOP_VARIABLE == 0) {
                            CoursesActivity.FIRST_COURSE = (document.getString("name")).toString()
                            CoursesActivity.FIRST_COURSE_ID = (document.id)
                            CoursesActivity.LOOP_VARIABLE += 1
                            continue
                        }
                        matchingCourses = (document.getString("name")).toString()
                        val studyPeriod = StudyPeriod(
                            CoursesActivity.FIRST_COURSE,
                            matchingCourses,
                            "Period 3",
                            CoursesActivity.FIRST_COURSE_ID,
                            course2_id = document.id
                        )
                        dataset.add(studyPeriod)

                        CoursesActivity.LOOP_VARIABLE = 0

                    } else if (document.getLong("period") == 4.toLong()) {

                        if (CoursesActivity.LOOP_VARIABLE == 0) {
                            CoursesActivity.FIRST_COURSE = (document.getString("name")).toString()
                            CoursesActivity.FIRST_COURSE_ID = (document.id)
                            CoursesActivity.LOOP_VARIABLE += 1
                            continue
                        }
                        matchingCourses = (document.getString("name")).toString()
                        val studyPeriod = StudyPeriod(
                            CoursesActivity.FIRST_COURSE,
                            matchingCourses,
                            "Period 4",
                            CoursesActivity.FIRST_COURSE_ID,
                            course2_id = document.id
                        )
                        dataset.add(studyPeriod)

                        CoursesActivity.LOOP_VARIABLE = 0
                    }

                }
                viewManager = LinearLayoutManager(context)
                viewAdapter = CourseRecyclerViewAdapter(dataset, context!!)

                coursesRecyclerView =
                    root.findViewById<RecyclerView>(R.id.coursesRecyclerView).apply {
                        setHasFixedSize(true)

                        layoutManager = viewManager

                        adapter = viewAdapter
                    }
            }

            .addOnFailureListener { exception ->
                Log.w("Could not find", "Error getting documents: ", exception)
            }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CoursesViewModel::class.java)
    }

}
