package com.example.dimpguide.ui.home.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.dimpguide.ActingMainActivity
import com.example.dimpguide.CoursesActivity

import com.example.dimpguide.R
import com.example.dimpguide.ui.home.home.HomeFragment
import kotlinx.android.synthetic.main.programme_fragment.view.*

class ProgrammeFragment : Fragment(), View.OnClickListener {

    private lateinit var myContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_programme_year, container, false)

        val programs = resources.getStringArray(R.array.Programmes)
        val spinner = view?.findViewById<Spinner>(R.id.chooseProgramme)

        if (spinner != null) {
            val spinnerAdapter = ArrayAdapter(
                activity!!.applicationContext,
                android.R.layout.simple_spinner_item,
                programs
            )
            spinner.adapter = spinnerAdapter
        }


        root.yearOneButton.setOnClickListener{
            onClick(it)
        }


        return root
    }

    override fun onAttach(context: Context?) {
        myContext = context!!
        super.onAttach(context)

    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.yearOneButton) {
            findNavController().navigate(R.id.coursesFragment)
        } else if (id == R.id.yearTwoButton) {
            findNavController().navigate(R.id.coursesFragment)
        } else if (id == R.id.yearThreeButton) {
            findNavController().navigate(R.id.coursesFragment)
        }
    }

}
