package com.example.dimpguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FAQActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val dataset:Array<FAQ> = arrayOf(FAQ("WHY ARE WE STILL ALIVE","NICOLAS CAGE"),
            FAQ("WHY AM I HERE","SATAN")
        )

        viewManager = LinearLayoutManager(this)

        viewAdapter = FAQRecyclerViewAdapter(dataset,this)

        findViewById<RecyclerView>(R.id.FAQRecyclerView).apply{
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter


        }
    }
}
