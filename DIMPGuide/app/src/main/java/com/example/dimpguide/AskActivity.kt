package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dimpguide.DbHandler.Companion.db
import kotlinx.android.synthetic.main.activity_ask.*
import kotlinx.android.synthetic.main.activity_specific_course.*

class AskActivity : BaseFunctionsForAllActivities() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)


        submitButton.setOnClickListener {
            val questionData = hashMapOf(
                "question" to enterQuestionText.text.toString()
            )
            db.collection("FAQ")
                .add(questionData)
            startActivity(Intent(this, Specific_course::class.java))
            Toast.makeText(this,getString(R.string.QuestionBeenSubmitted), Toast.LENGTH_LONG).show()
        }


        subjectText.addTextChangedListener(object:TextWatcher{
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
                    questionErrorText.text = getString(R.string.ToShortQuestion)
                }else if(s!!.length > MAXIMUM_QUESTION_LENGTH){
                    questionErrorText.text = getString(R.string.toLongQuestion)
                }else{
                    questionErrorText.text = ""
                    submitButton.isEnabled = true
                }
            }

        })
    }

    companion object{
        const val MINIMUM_QUESTION_LENGTH = 5
        const val MAXIMUM_QUESTION_LENGTH = 100
        const val MINIMUM_SUBJECT_LENGTH = 3
        const val MAXIMUM_SUBJECT_LENGTH = 20
    }


}