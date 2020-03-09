package com.example.dimpguide.ui.home.programme

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.dimpguide.CoursesActivity

import com.example.dimpguide.R
import com.example.dimpguide.ui.home.home.HomeFragment
import kotlinx.android.synthetic.main.programme_fragment.view.*

class ProgrammeFragment : Fragment() {

    private lateinit var viewModel: ProgrammeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.programme_fragment,container,false)
        val programs = resources.getStringArray(R.array.Programmes)
        val spinner = root.findViewById<Spinner>(R.id.chooseProgramme)

        if(spinner != null){
            val spinnerAdapter = ArrayAdapter(activity!!.applicationContext,android.R.layout.simple_spinner_item,programs)
            spinner.adapter = spinnerAdapter
        }

        root.yearOneButton.setOnClickListener {
            val intent = Intent(activity!!.applicationContext, HomeFragment::class.java )
            intent.putExtra("Year","yearOne")
            startActivity(intent)
        }
        root.yearTwoButton.setOnClickListener {
            val intent = Intent(activity!!.applicationContext,CoursesActivity::class.java)
            intent.putExtra("Year","yearTwo")
            startActivity(intent)
        }
        root.yearThreeButton.setOnClickListener {
            val intent = Intent(activity!!.applicationContext,CoursesActivity::class.java)
            intent.putExtra("Year","yearThree")
            startActivity(intent)
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProgrammeViewModel::class.java)
        Log.i("","")
    }

}
