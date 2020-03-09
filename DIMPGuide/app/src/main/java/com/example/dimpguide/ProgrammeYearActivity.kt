package com.example.dimpguide

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_programme_year.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_programme_year.*



class ProgrammeYearActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programme_year)

        val programs = resources.getStringArray(R.array.Programmes)
        val spinner = findViewById<Spinner>(R.id.chooseProgramme)

        if (spinner != null) {
            val spinnerAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, programs)
            spinner.adapter = spinnerAdapter
        }
        yearOneButton.setOnClickListener {
            val intent = Intent(this, CoursesActivity::class.java)
            val chosenProgram = spinner.selectedItem.toString()
            intent.putExtra("Program", chosenProgram)
            intent.putExtra("Year", "1")
            startActivity(intent)
        }
        yearTwoButton.setOnClickListener {
            val intent = Intent(this, CoursesActivity::class.java)
            val chosenProgram = spinner.selectedItem.toString()
            intent.putExtra("Program", chosenProgram)
            intent.putExtra("Year", "2")
            startActivity(intent)
        }
        yearThreeButton.setOnClickListener {
            val intent = Intent(this, CoursesActivity::class.java)
            val chosenProgram = spinner.selectedItem.toString()
            intent.putExtra("Program", chosenProgram)
            intent.putExtra("Year", "3")
            startActivity(intent)
        }

    }


    override fun onBackPressed() {
        Toast.makeText(this, "Tap twice to close the app", Toast.LENGTH_LONG).show()
        if (DbHandler.fUser != null && timesBackButtonPressed < maxButtonPressed) {
            timesBackButtonPressed += 1
            //doesn't reset after 2nd sign in
            Log.i(
                "programme",
                "Times clicker back counter = " + timesBackButtonPressed.toString()
            )
            if (timesBackButtonPressed >= 3) {
                finishAffinity()
                timesBackButtonPressed = 0
            }

        } else {
            super.onBackPressed()
        }
    }

    companion object {
        var timesBackButtonPressed = 0
        const val maxButtonPressed = 3
    }
}
