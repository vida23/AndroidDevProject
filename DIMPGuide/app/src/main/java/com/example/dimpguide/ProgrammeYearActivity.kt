package com.example.dimpguide

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_programme_year.*

class ProgrammeYearActivity : BaseFunctionsForAllActivities() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programme_year)

        val programs = resources.getStringArray(R.array.Programmes)
        val spinner = findViewById<Spinner>(R.id.chooseProgramme)

        if(spinner != null){
            val spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,programs)
            spinner.adapter = spinnerAdapter
        }
        yearOneButton.setOnClickListener {
            val intent = Intent(this,CoursesActivity::class.java)
            intent.putExtra("Year","yearOne")
            startActivity(intent)
        }
        yearTwoButton.setOnClickListener {
            val intent = Intent(this,CoursesActivity::class.java)
            intent.putExtra("Year","yearTwo")
            startActivity(intent)
        }
        yearThreeButton.setOnClickListener {
            val intent = Intent(this,CoursesActivity::class.java)
            intent.putExtra("Year","yearThree")
            startActivity(intent)
        }

        if (LoggedInManager.isLoggedIn == true) {
            Log.i("SignedIn", "User is signed in")
        } else {
            // No user is signed in
            Log.i("SignedIn", "User is NOT signed in")
        }

    }


    override fun onBackPressed() {
        if(LoggedInManager.isLoggedIn && timesBackButtonPressed< maxButtonPressed){
            timesBackButtonPressed+=1
            //doesn't reset after 2nd sign in


        }else {
            LoggedInManager.isLoggedIn = false
            super.onBackPressed()
        }
    }
    companion object{
        var timesBackButtonPressed = 0
        const val maxButtonPressed = 1
    }
}
