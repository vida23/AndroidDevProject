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
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.dimpguide.ActingMainActivity
import com.example.dimpguide.CoursesActivity

import com.example.dimpguide.R
import kotlinx.android.synthetic.main.programme_fragment.*
import kotlinx.android.synthetic.main.programme_fragment.view.*

class ProgrammeFragment : Fragment(), View.OnClickListener {

    private lateinit var myContext: Context
    private lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_programme_year, container, false)

        val programs = resources.getStringArray(R.array.Programmes)
        spinner = root.findViewById<Spinner>(R.id.chooseProgramme)

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

        root.yearTwoButton.setOnClickListener{
            onClick(it)
        }

        root.yearThreeButton.setOnClickListener{
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

            val chosenProgram = spinner.selectedItem.toString()

            val bundle = bundleOf(
                "Program" to chosenProgram,
                "Year" to "1"
            )

            findNavController().navigate(R.id.coursesFragment, bundle)


        } else if (id == R.id.yearTwoButton) {

            val chosenProgram = spinner.selectedItem.toString()

            var bundle = bundleOf(
                "Program" to chosenProgram,
                "Year" to "2"
            )
            findNavController().navigate(R.id.coursesFragment,bundle)
        } else if (id == R.id.yearThreeButton) {
            val chosenProgram = spinner.selectedItem.toString()

            var bundle = bundleOf(
                "Program" to chosenProgram,
                "Year" to "3"
            )
            findNavController().navigate(R.id.coursesFragment,bundle)
        }
    }

}
