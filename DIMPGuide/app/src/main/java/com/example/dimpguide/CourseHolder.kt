package com.example.dimpguide

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view : View = v
    private var course: Course? = null


    init {
        v.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
       Log.d("RecyclerView","Click")
    }


    companion object {
        private val COURSE_KEY = "COURSE"
    }
}