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

        val dataset:Array<FAQ> = arrayOf(FAQ("TELL ME WHY","NICOLAS CAGEfhdghdgfgfgfgfsg fdsfdsgsg d dfgfd hgfdgdf gd g fdgdf gfd gfd gfd g"),
            FAQ("WHY AM I HERE","Because nice")
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
