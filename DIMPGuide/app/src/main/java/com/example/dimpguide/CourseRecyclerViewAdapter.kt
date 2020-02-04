package com.example.dimpguide

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class CourseRecyclerViewAdapter(private val Dataset: Array<Course>) :
    RecyclerView.Adapter<CourseRecyclerViewAdapter.MyViewHolder>(){


    class MyViewHolder(v:View) :RecyclerView.ViewHolder(v) , View.OnClickListener {
        private var view : View = v
        private var course: Course? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
           Log.d("RecyclerView","Click")
        }

        fun bindCourse(course: Course){
            this.course = course
            view.course1.text = course.course1
            view.course2.text = course.course2
            view.month.text = course.month

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

            val itemCourses = Dataset[position]
            holder.bindCourse(itemCourses)
        }

        override fun getItemCount(): Int {
            return Dataset.size
        }

}