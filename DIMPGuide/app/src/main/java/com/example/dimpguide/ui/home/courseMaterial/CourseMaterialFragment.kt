package com.example.dimpguide.ui.home.courseMaterial

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimpguide.R


class CourseMaterialFragment : Fragment() {

    companion object {
        fun newInstance() = CourseMaterialFragment()
    }

    private lateinit var viewModel: CourseMaterialViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.course_material_fragment, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CourseMaterialViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
