package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val programs = resources.getStringArray(R.array.Programmes)
        val years = resources.getStringArray(R.array.Years)
        val spinnerPrograms = findViewById<Spinner>(R.id.programme)
        val spinnerYears = findViewById<Spinner>(R.id.year)
        signUpButton.isEnabled = false
        if(spinnerPrograms != null){
            val spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,programs)
            spinnerPrograms.adapter = spinnerAdapter
        }
        if(spinnerYears != null){
            val spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,years)
            spinnerYears.adapter = spinnerAdapter
        }
        signUpButton.setOnClickListener{
            val intent = Intent(this,ProgrammeYearActivity::class.java)
            startActivity(intent)
        }
        username.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                signUpButton.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length < MIN_USERNAME_LENGTH){
                    usernameErrorText.text = getString(R.string.toShortUsername)
                    signUpButton.isEnabled = false
                }else{
                    signUpButton.isEnabled = true
                    usernameErrorText.text = ""
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(password.text.length < MIN_PASSWORD_LENGTH ||  !email.text.contains("@")){
                    signUpButton.isEnabled = false
                }
            }
        })
        password.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                signUpButton.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length < MIN_PASSWORD_LENGTH){
                    passwordErrorText.text = getString(R.string.toShortPassword)
                    signUpButton.isEnabled = false
                }else{
                    signUpButton.isEnabled = true
                    passwordErrorText.text = ""
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(username.text.length < MIN_USERNAME_LENGTH || !email.text.contains("@")){
                    signUpButton.isEnabled = false
                }
            }
        })
        email.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                signUpButton.isEnabled = false
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s!!.contains("@")){
                    emailErrorText.text = getString(R.string.emailErrorNoAt)
                    signUpButton.isEnabled = false
                }else{
                    signUpButton.isEnabled = true
                    emailErrorText.text = ""
                }

            }

            override fun afterTextChanged(s: Editable?) {
                if(username.text.length < MIN_USERNAME_LENGTH || password.text.length < MIN_PASSWORD_LENGTH){
                    signUpButton.isEnabled = false
                }
            }

        })
    }
    companion object Companion{
        const val MIN_USERNAME_LENGTH = 2
        const val MIN_PASSWORD_LENGTH = 8
    }
}

