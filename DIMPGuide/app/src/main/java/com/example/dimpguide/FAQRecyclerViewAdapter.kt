package com.example.dimpguide

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.faq_recycler_view_item.view.*

class FAQRecyclerViewAdapter(private val dataset: Array<FAQ>,private val context: Context) : RecyclerView.Adapter<FAQRecyclerViewAdapter.FAQViewHolder>(){


    class FAQViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view : View = v
        fun bindFAQ(FAQ: FAQ,context: Context){
            view.Question.text = FAQ.question
            view.FAQSpinner.adapter = ArrayAdapter(context,android.R.layout.simple_spinner_item,
                arrayOf(FAQ.answer))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQRecyclerViewAdapter.FAQViewHolder {
        val inflatedView = parent.inflate(R.layout.faq_recycler_view_item,false)
        return FAQViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: FAQViewHolder, position: Int) {
        val itemFAQ = dataset[position]
        holder.bindFAQ(itemFAQ,context)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}