package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}
}
