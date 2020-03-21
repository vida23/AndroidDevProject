package com.example.dimpguide.ui.home.fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.dimpguide.DbHandler

import com.example.dimpguide.R

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
        val progressBar = root.findViewById<ProgressBar>(R.id.progress_bar_ask)
        progressBar.visibility = View.INVISIBLE

        val subjectErrorText = root.findViewById<TextView>(R.id.subject_error_text)
        val submitButton = root.findViewById<Button>(R.id.submit_button)
        val questionErrorText = root.findViewById<TextView>(R.id.question_error_text)
        val enterQuestionText = root.findViewById<EditText>(R.id.enter_question_text)
        val subjectText = root.findViewById<EditText>(R.id.subject_text)

        submitButton.isEnabled = false

        submitButton.setOnClickListener {
            val questionData = hashMapOf(
                "question" to enterQuestionText.text.toString()
            )
            DbHandler.db.collection("FAQ")
                .add(questionData).apply {
                    progressBar.visibility = View.VISIBLE
                }
                .addOnSuccessListener {

                    progressBar.visibility = View.INVISIBLE

                    fragmentManager!!.popBackStack()

                    Toast.makeText(activity!!.applicationContext,getString(R.string.question_submitted), Toast.LENGTH_LONG).show()


                }
        }

        root.findViewById<EditText>(R.id.subject_text).addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                submitButton.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length < MINIMUM_SUBJECT_LENGTH){
                    subjectErrorText.text= getString(R.string.to_short_subject)
                }else if(s!!.length > MAXIMUM_SUBJECT_LENGTH){
                    subjectErrorText.text = getString(R.string.to_long_subject)
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

        enterQuestionText.addTextChangedListener(object : TextWatcher {
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
                    questionErrorText.text = getString(R.string.to_short_question)
                }else if(s!!.length > MAXIMUM_QUESTION_LENGTH){
                    questionErrorText.text = getString(R.string.to_long_question)
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
