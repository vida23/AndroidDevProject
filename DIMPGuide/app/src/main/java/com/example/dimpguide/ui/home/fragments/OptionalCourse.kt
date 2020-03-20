package com.example.dimpguide.ui.home.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.dimpguide.DbHandler
import com.example.dimpguide.DbHandler.Companion.db

import com.example.dimpguide.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.recycler_view_item.*

/**
 * A simple [Fragment] subclass.
 */
class OptionalCourse : Fragment() {

    fun setOptionalCourse(courseId: String, courseName: String) {
        db.collection("users")
            .whereEqualTo("uid", FirebaseAuth.getInstance().currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                if (!document.isEmpty) {
                    for (doc in document) {
                        doc.id

                        val docRef = db.collection("users").document(doc.id)
                        docRef.get()
                            .addOnSuccessListener {
                                if (courseName == "Valbar 1") {
                                    if (courseId == doc.getString("optionalCourseTwo")){
                                        Toast.makeText(activity?.applicationContext, "Course already picked", Toast.LENGTH_SHORT).show()
                                    }else{
                                        docRef.update("optionalCourseOne", courseId)
                                        fragmentManager!!.popBackStack()
                                    }
                                } else {
                                    if (courseId == doc.getString("optionalCourseOne")){
                                        Toast.makeText(activity!!.applicationContext, "Course already picked", Toast.LENGTH_SHORT).show()
                                    }else{
                                        docRef.update("optionalCourseTwo", courseId)
                                        fragmentManager!!.popBackStack()
                                    }
                                }
                            }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.i("database", "WE DON'T GOT A MATCHING USER")
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_optional_course, container, false)

        val courseName = arguments?.getString("name").toString()

        root.findViewById<Button>(R.id.optional_one)
            .setOnClickListener {
                val pickCourseId = "Matematisk statistik"
                setOptionalCourse(pickCourseId,courseName)

            }
        root.findViewById<Button>(R.id.optional_two)
            .setOnClickListener {
                val pickCourseId = "User Research"
                setOptionalCourse(pickCourseId,courseName)

            }
        root.findViewById<Button>(R.id.optional_three)
            .setOnClickListener {
                val pickCourseId = "AlMachine Learning"
                setOptionalCourse(pickCourseId,courseName)

            }
        root.findViewById<Button>(R.id.optional_four)
            .setOnClickListener {
                val pickCourseId = "Information Security"
                setOptionalCourse(pickCourseId,courseName)

            }

        return root
    }


}
