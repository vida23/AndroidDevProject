package com.example.dimpguide.ui.home.Courses

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dimpguide.Course
import com.example.dimpguide.CourseRecyclerViewAdapter

import com.example.dimpguide.R

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

        val dataset:Array<Course> = arrayOf(
            Course("Diskret Matematik","Objektorienterad mjukvaruutveckling med designmönster","Augusti"),
            Course("Linjär ALgebra", "Introduktion Till programmejring","Oktober") ,
            Course("Diskret Matematik","Datateknisk Introduktionskurs","Augusti"),
            Course("Linjär ALgebra", "Introduktion Till programmejring","Oktober")
        )

        viewManager = LinearLayoutManager(root.context)
        viewAdapter = CourseRecyclerViewAdapter(dataset,root.context)

        coursesRecyclerView = root.findViewById<RecyclerView>(R.id.coursesRecyclerView).apply{
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CoursesViewModel::class.java)
    }

}
