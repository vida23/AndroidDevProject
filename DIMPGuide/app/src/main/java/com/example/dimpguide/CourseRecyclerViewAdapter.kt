package com.example.dimpguide

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.navigation.findNavController
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import kotlin.math.log

class CourseRecyclerViewAdapter(
    private val dataset: MutableList<StudyPeriod>,
    private val context: Context
) :
    RecyclerView.Adapter<CourseRecyclerViewAdapter.MyViewHolder>() {


    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var studyPeriod: StudyPeriod? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "Click")
        }

        fun bindCourse(studyPeriod: StudyPeriod, context: Context) {
            this.studyPeriod = studyPeriod
            view.course1.text = studyPeriod.course1
            view.course2.text = studyPeriod.course2
            view.period.text = studyPeriod.period


            if (studyPeriod.course2 == "Valbar 2" || studyPeriod.course2 == "Valbar 1") {
                view.course2.setBackgroundResource(R.drawable.ic_add_black_24dp)
            }


            view.course1.setOnClickListener {
                /*val intent = Intent(context,Specific_course::class.java)
                intent.putExtra("name",studyPeriod.course1)
                intent.putExtra("course_id",studyPeriod.course1_id)
                startActivity(context,intent,null)*/
                var bundle = bundleOf(
                    "name" to studyPeriod.course1,
                    "course_id" to studyPeriod.course1_id
                )
                view.findNavController().navigate(R.id.specificCourseFragment, bundle)
            }
            view.course2.setOnClickListener {
                /*val intent = Intent(context,Specific_course::class.java)
                intent.putExtra("name",studyPeriod.course2)
                intent.putExtra("course_id",studyPeriod.course2_id)
                startActivity(context,intent,null)*/
                if ((studyPeriod.course2 == "Valbar 2" || studyPeriod.course2 == "Valbar 1") && FirebaseAuth.getInstance().currentUser != null) {
                    view.findNavController().navigate(R.id.optionalCourseFragment)
                } else if ((studyPeriod.course2 == "Valbar 1" || studyPeriod.course2 == "Valbar 2") && FirebaseAuth.getInstance().currentUser == null){
                    Toast.makeText(context, "Sign in", Toast.LENGTH_SHORT).show()
                }
                else{

                    var bundle = bundleOf(
                        "name" to studyPeriod.course2,
                        "course_id" to studyPeriod.course2_id
                    )
                    view.findNavController().navigate(R.id.specificCourseFragment, bundle)
                    /*val intent = Intent(context,Specific_course::class.java)
                    intent.putExtra("name",course.course1)
                    startActivity(context,intent,null)*/
                }


            }
        }

        companion object {
            private val COURSE_KEY = "COURSE"
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseRecyclerViewAdapter.MyViewHolder {
        val inflatedView = parent.inflate(R.layout.recycler_view_item, false)
        return MyViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val itemCourses = dataset[position]
        holder.bindCourse(itemCourses, context)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}