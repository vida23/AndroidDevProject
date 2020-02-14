package com.example.dimpguide

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class CourseRecyclerViewAdapter(private val Dataset: Array<Course>, private val context: Context) :
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

        fun bindCourse(course: Course, context: Context){
            this.course = course
            view.course1.text = course.course1
            view.course2.text = course.course2
            view.month.text = course.month

            view.course1.setOnClickListener {
                val intent = Intent(context,Specific_course::class.java)
                intent.putExtra("name",course.course1)
                startActivity(context,intent,null)
            }
            view.course2.setOnClickListener {
                val intent = Intent(context,Specific_course::class.java)
                intent.putExtra("name",course.course2)
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

            val itemCourses = Dataset[position]
            holder.bindCourse(itemCourses,context)
        }

        override fun getItemCount(): Int {
            return Dataset.size
        }

}