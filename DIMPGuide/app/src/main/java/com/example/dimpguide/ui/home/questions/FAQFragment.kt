package com.example.dimpguide.ui.home.questions

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


        viewManager = LinearLayoutManager(context)


        root.findViewById<RecyclerView>(R.id.FAQRecyclerView).apply{
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter


        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FAQViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
