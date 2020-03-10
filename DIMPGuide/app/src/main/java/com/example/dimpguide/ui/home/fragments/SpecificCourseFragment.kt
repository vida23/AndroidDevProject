package com.example.dimpguide.ui.home.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.dimpguide.*

class SpecificCourseFragment : Fragment() {

    companion object {
        fun newInstance() = SpecificCourseFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.specific_course_fragment, container, false)
        root.findViewById<TextView>(R.id.Course).apply{
            //activity!!.applicationContext.text = name
        }

        val name = arguments?.getString("name")
        val course_id = arguments?.getString("course_id")

        root.findViewById<Button>(R.id.CourseMaterialButton)
            .setOnClickListener {
                findNavController().navigate(R.id.courseMaterialFragment)
            }

        root.findViewById<Button>(R.id.FAQButton)
            .setOnClickListener {
                findNavController().navigate(R.id.FAQFragment)
            }
        root.findViewById<Button>(R.id.AskButton)
            .setOnClickListener {
                findNavController().navigate(R.id.askFragment)
            }
        root.findViewById<Button>(R.id.GoodToKnowButton)
            .setOnClickListener {

                var bundle = bundleOf(
                    "name" to name,
                    "course_id" to course_id
                )
                findNavController().navigate(R.id.courseMaterialFragment, bundle)
            }
        return root
    }

}
