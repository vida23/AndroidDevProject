package com.example.dimpguide.ui.home.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.dimpguide.*
import com.google.firebase.auth.FirebaseAuth

class SpecificCourseFragment : Fragment() {

    companion object {
        fun newInstance() = SpecificCourseFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.specific_course_fragment, container, false)

        val name = arguments?.getString("name")
        val course_id = arguments?.getString("course_id")

        root.findViewById<TextView>(R.id.Course).apply{
            text = name
        }

        root.findViewById<Button>(R.id.CourseMaterialButton)
            .setOnClickListener {
                findNavController().navigate(R.id.courseMaterialFragment)
            }

        root.findViewById<Button>(R.id.FAQButton)
            .setOnClickListener {
                findNavController().navigate(R.id.FAQFragment)
            }

        if (FirebaseAuth.getInstance().currentUser != null){
            root.findViewById<Button>(R.id.AskButton)
                .setOnClickListener {
                    findNavController().navigate(R.id.askFragment)
                }
        }
        else{
            root.findViewById<Button>(R.id.AskButton)
                .setOnClickListener {
                    Toast.makeText(activity!!.applicationContext, "Add string resource", Toast.LENGTH_SHORT).show()
                }
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
