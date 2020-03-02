package com.example.dimpguide

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class CourseRecyclerViewAdapter(private val dataset: MutableList<StudyPeriod>, private val context: Context) :
       RecyclerView.Adapter<CourseRecyclerViewAdapter.MyViewHolder>(){


    class MyViewHolder(v:View) :RecyclerView.ViewHolder(v) , View.OnClickListener {
        private var view : View = v
        private var studyPeriod: StudyPeriod? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
           Log.d("RecyclerView","Click")
        }

        fun bindCourse(studyPeriod: StudyPeriod, context: Context){
            this.studyPeriod = studyPeriod
            view.course1.text = studyPeriod.course1
            view.course2.text = studyPeriod.course2
            view.period.text = studyPeriod.period

            view.course1.setOnClickListener {
                val intent = Intent(context,Specific_course::class.java)
                intent.putExtra("name",studyPeriod.course1)
                startActivity(context,intent,null)
            }
            view.course2.setOnClickListener {
                val intent = Intent(context,Specific_course::class.java)
                intent.putExtra("name",studyPeriod.course2)
                startActivity(context,intent,null)
            }

        }

        companion object{
            private val COURSE_KEY = "COURSE"
        }
    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseRecyclerViewAdapter.MyViewHolder {
            val inflatedView = parent.inflate(R.layout.recycler_view_item,false)
            return MyViewHolder(inflatedView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val itemCourses = dataset[position]
            holder.bindCourse(itemCourses,context)
        }

        override fun getItemCount(): Int {
            return dataset.size
        }

}