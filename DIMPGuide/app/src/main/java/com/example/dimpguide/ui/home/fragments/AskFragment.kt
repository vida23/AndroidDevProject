package com.example.dimpguide.ui.home.fragments


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.dimpguide.DbHandler

import com.example.dimpguide.R
import com.example.dimpguide.Specific_course
import kotlinx.android.synthetic.main.activity_ask.*

/**
 * A simple [Fragment] subclass.
 */
class AskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_ask, container, false)

        val subjectErrorText = root.findViewById<TextView>(R.id.subjectErrorText)
        val submitButton = root.findViewById<Button>(R.id.submitButton)
        val questionErrorText = root.findViewById<TextView>(R.id.questionErrorText)

        submitButton.setOnClickListener {
            val questionData = hashMapOf(
                "question" to enterQuestionText.text.toString()
            )
            DbHandler.db.collection("FAQ")
                .add(questionData)
            findNavController().navigate(R.id.specificCourseFragment)
            Toast.makeText(activity!!.applicationContext,"Your question has been submitted!", Toast.LENGTH_LONG).show()
        }

        root.findViewById<EditText>(R.id.subjectText).addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                submitButton.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length < MINIMUM_SUBJECT_LENGTH){
                    subjectErrorText.text= getString(R.string.ToShortSubject)
                }else if(s!!.length > MAXIMUM_SUBJECT_LENGTH){
                    subjectErrorText.text = getString(R.string.ToLongSubject)
                }else{
                    subjectErrorText.text = ""
                    submitButton.isEnabled =true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(enterQuestionText.text.length< MINIMUM_QUESTION_LENGTH || enterQuestionText.text.length> MAXIMUM_QUESTION_LENGTH){
                    submitButton.isEnabled = false
                }
            }
        })

        root.findViewById<EditText>(R.id.enterQuestionText).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(subjectText.text.length < MINIMUM_SUBJECT_LENGTH || subjectText.text.length > MAXIMUM_SUBJECT_LENGTH){

                    submitButton.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                submitButton.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length < MINIMUM_QUESTION_LENGTH){
                    questionErrorText.text = getString(R.string.ToShortQuestion)
                }else if(s!!.length > MAXIMUM_QUESTION_LENGTH){
                    questionErrorText.text = getString(R.string.toLongQuestion)
                }else{
                    questionErrorText.text = ""
                    submitButton.isEnabled = true
                }
            }

        })

        return root
    }

    companion object{
        const val MINIMUM_QUESTION_LENGTH = 5
        const val MAXIMUM_QUESTION_LENGTH = 100
        const val MINIMUM_SUBJECT_LENGTH = 3
        const val MAXIMUM_SUBJECT_LENGTH = 20
    }
}
