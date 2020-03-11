package com.example.dimpguide.ui.home.courseMaterial

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dimpguide.DbHandler.Companion.db
import com.example.dimpguide.R
import org.w3c.dom.Text


class CourseMaterialFragment : Fragment() {

    companion object {
        fun newInstance() = CourseMaterialFragment()
    }

    private lateinit var viewModel: CourseMaterialViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.course_material_fragment, container, false)
        val courseName = arguments?.getString("name")
        val courseId = arguments?.getString("course_id")

        val courseTitle = root.findViewById<TextView>(R.id.CourseTitle)
        courseTitle.text = courseName

        db.collection("courses").document(courseId.toString())
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    root.findViewById<TextView>(R.id.NumberOfHp).apply {
                        this.text = document.get("hp").toString()
                    }
                    root.findViewById<TextView>(R.id.courseDescription).apply {
                        this.text = document.get("des").toString()
                    }
                    root.findViewById<TextView>(R.id.CoursePreknowledge).apply {
                        this.text = document.get("pre").toString()
                    }
                }

            }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CourseMaterialViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
