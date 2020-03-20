package com.example.dimpguide.ui.home.questions

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dimpguide.DbHandler
import com.example.dimpguide.FAQ
import com.example.dimpguide.FAQRecyclerViewAdapter

import com.example.dimpguide.R

class FAQFragment : Fragment() {

    companion object {
        fun newInstance() = FAQFragment()
    }

    private lateinit var viewModel: FAQViewModel
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.faq_fragment, container, false)

        val dataset: MutableList<FAQ> = ArrayList()

        DbHandler.db.collection("FAQ")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    if(document.getString("answer")!= null) {
                        val faq = FAQ(
                            document.getString("question").toString(),
                            document.getString("answer").toString()
                        )
                        dataset.add(faq)
                    }
                }

                viewManager = LinearLayoutManager(activity!!.applicationContext)
                viewAdapter = FAQRecyclerViewAdapter(dataset,activity!!.applicationContext)


                root.findViewById<RecyclerView>(R.id.FAQRecyclerView).apply{
                    setHasFixedSize(true)

                    layoutManager = viewManager

                    adapter = viewAdapter

                    root.findViewById<ProgressBar>(R.id.progressBarFAQ).visibility = View.INVISIBLE
                }

            }
            .addOnFailureListener {exception ->
                Log.w("Cannot find file", "Error getting document", exception)

                root.findViewById<ProgressBar>(R.id.progressBarFAQ).visibility = View.INVISIBLE
                Toast.makeText(context,"Could not fetch the data",Toast.LENGTH_SHORT).show()

            }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FAQViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
