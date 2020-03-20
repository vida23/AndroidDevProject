package com.example.dimpguide.ui.home.courseMaterial

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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

        val courseTitle = root.findViewById<TextView>(R.id.course_title)
        courseTitle.text = courseName

        db.collection("courses").document(courseId.toString())
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    root.findViewById<TextView>(R.id.number_of_hp).apply {
                        this.text = document.get("hp").toString() + "hp"
                    }
                    root.findViewById<TextView>(R.id.course_description).apply {
                        this.text = document.get("des").toString()
                    }
                    root.findViewById<TextView>(R.id.course_preknowledge).apply {
                        this.text = getString(R.string.course_preknow) + ": "  + document.get("pre").toString()
                    }
                    root.findViewById<ProgressBar>(R.id.progress_bar_course_material).visibility = View.INVISIBLE
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
