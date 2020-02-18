package com.example.dimpguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_ask.*

class AskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
        subjectText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                submitButton.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length < 3){
                    subjectErrorText.text= getString(R.string.ToShort)
                }else if(s!!.length >20){
                    subjectErrorText.text = getString(R.string.ToLong)
                }else{
                    subjectErrorText.text = ""
                    submitButton.isEnabled =true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(EnterQuestionText.text.length<5 || EnterQuestionText.text.length>100){
                    submitButton.isEnabled = false
                }
            }
        })
        EnterQuestionText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(subjectText.text.length < 3 || subjectText.text.length>20){
                    submitButton.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                submitButton.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length < 5){
                    questionErrorText.text = getString(R.string.ToShort)
                }else if(s!!.length > 100){
                    questionErrorText.text = getString(R.string.ToLong)
                }else{
                    questionErrorText.text = ""
                    submitButton.isEnabled = true
                }
            }

        })
    }


}
