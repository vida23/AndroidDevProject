package com.example.dimpguide

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.faq_recycler_view_item.view.*

class FAQRecyclerViewAdapter(private val dataset: MutableList<FAQ>,private val context: Context) : RecyclerView.Adapter<FAQRecyclerViewAdapter.FAQViewHolder>(){


    class FAQViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view : View = v
        private var isAnswerShowing : Boolean = true

        fun bindFAQ(FAQ: FAQ,context: Context){
            if(isAnswerShowing == true){
                view.answer.visibility = View.INVISIBLE
                isAnswerShowing = false
            }
            view.question.text = FAQ.question
            view.toggle_button.setOnClickListener {
                if(view.answer.visibility == View.VISIBLE){
                    view.answer.visibility = View.INVISIBLE
                    view.answer.text = ""
                    view.answer.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0)
                }else{
                    view.answer.visibility = View.VISIBLE
                    view.answer.text = FAQ.answer
                    view.answer.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQViewHolder {
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