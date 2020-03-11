package com.example.dimpguide.ui.home.fragments

import android.content.ContentValues.TAG
import android.media.Rating
import android.os.Bundle
import android.util.Log
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
import com.example.dimpguide.DbHandler.Companion.db
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import kotlinx.android.synthetic.main.specific_course_fragment.*

class SpecificCourseFragment : Fragment() {

    companion object {
        fun newInstance() = SpecificCourseFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.specific_course_fragment, container, false)
        root.findViewById<TextView>(R.id.Course).apply {
            //activity!!.applicationContext.text = name
        }

        val name = arguments?.getString("name")
        val course_id = arguments?.getString("course_id")

        var avgRating: Long
        val givenRate = root.findViewById<RatingBar>(R.id.CourseRatingBar)


        val docRef = db.collection("courses").document(course_id.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {

                    givenRate.rating = document.getLong("avgRate")!!.toFloat()
                    Log.d("stars", document.getLong("avgRate").toString())
                    if (FirebaseAuth.getInstance().currentUser != null && document.getString("uids")!!.contains(FirebaseAuth.getInstance().currentUser!!.uid) == false) {
                        Log.d("this", document.getString("uids")!!.contains(FirebaseAuth.getInstance().currentUser!!.uid).toString())

                        rateSubmitButton.setOnClickListener {

                            val ratingCount = document.getLong("ratingCount")!! + 1
                            docRef.update("ratingCount", ratingCount)

                            val totalRating =
                                document.getLong("totalRating")!! + givenRate.rating.toLong()
                            docRef.update("totalRating", totalRating)

                            val updateUids =
                                document.getString("uids")!! + FirebaseAuth.getInstance().currentUser!!.uid + ", "
                            docRef.update("uids", updateUids)

                            avgRating = totalRating / ratingCount
                            docRef.update("avgRate", avgRating)

                            Toast.makeText(
                                activity!!.applicationContext,
                                getString(R.string.ThankYouRating),
                                Toast.LENGTH_LONG
                            ).show()
                            rateSubmitButton.isEnabled = false
                            givenRate.isEnabled = false
                        }

                    } else {
                        rateSubmitButton.isEnabled = false
                        givenRate.isEnabled = false
                    }


                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
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