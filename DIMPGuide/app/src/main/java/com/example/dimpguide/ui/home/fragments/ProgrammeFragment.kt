package com.example.dimpguide.ui.home.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController

import com.example.dimpguide.R
import kotlinx.android.synthetic.main.programme_fragment.view.*

class ProgrammeFragment : Fragment(), View.OnClickListener {

    private lateinit var myContext: Context
    private lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.programme_fragment, container, false)

        val programs = resources.getStringArray(R.array.Programmes)
        spinner = root.findViewById<Spinner>(R.id.choose_programme)

        if (spinner != null) {
            val spinnerAdapter = ArrayAdapter(
                activity!!.applicationContext,
                android.R.layout.simple_spinner_item,
                programs
            )
            spinner.adapter = spinnerAdapter
        }


        root.year_one_button.setOnClickListener{
            onClick(it)
        }

        root.year_two_button.setOnClickListener{
            onClick(it)
        }

        root.year_three_button.setOnClickListener{
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
        var chosenProgram = spinner.selectedItem.toString()

        if(chosenProgram == EMBEDDED_SYSTEMS_ENG){
            chosenProgram = EMBEDDED_SYSTEMS
        }else if(chosenProgram == SOFTWARE_DEVELOPMENT_ENG){
            chosenProgram = SOFTWARE_DEVELOPMENT
        }
        if (id == R.id.year_one_button) {

            val bundle = bundleOf(
                "Program" to chosenProgram,
                "Year" to "1"
            )

            findNavController().navigate(R.id.coursesFragment, bundle)


        } else if (id == R.id.year_two_button) {

            var bundle = bundleOf(
                "Program" to chosenProgram,
                "Year" to "2"
            )
            findNavController().navigate(R.id.coursesFragment,bundle)
        } else if (id == R.id.year_three_button) {

            var bundle = bundleOf(
                "Program" to chosenProgram,
                "Year" to "3"
            )
            findNavController().navigate(R.id.coursesFragment,bundle)
        }
    }
    companion object{
        const val SOFTWARE_DEVELOPMENT = "Mjukvaruutveckling och mobila plattformar"
        const val EMBEDDED_SYSTEMS  = "Inbyggda system"
        const val SOFTWARE_DEVELOPMENT_ENG = "Software Development"
        const val EMBEDDED_SYSTEMS_ENG = "Embedded Systems"
    }

}
