package com.example.dimpguide.ui.home.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.dimpguide.DbHandler
import com.example.dimpguide.DbHandler.Companion.db

import com.example.dimpguide.R
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 */
class OptionalCourse : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_optional_course, container, false)

        root.findViewById<Button>(R.id.optionalOne)
            .setOnClickListener {
                db.collection("users")
                    .whereEqualTo("uid", FirebaseAuth.getInstance().currentUser!!.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        if (!document.isEmpty){
                            for (doc in document){
                                doc.id

                                val docRef = db.collection("users").document(doc.id)
                                    docRef.get()
                                    .addOnSuccessListener {
                                        docRef.update("optionalCourseOne","Something")
                                        fragmentManager!!.popBackStack()
                                    }
                            }
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.i("database", "WE DON'T GOT A MATCHING USER")
                    }

            }
        root.findViewById<Button>(R.id.optionalTwo)
            .setOnClickListener {

            }
        root.findViewById<Button>(R.id.optionalThree)
            .setOnClickListener {

            }
        root.findViewById<Button>(R.id.optionalFour)
            .setOnClickListener {

            }

        return root
    }


}
