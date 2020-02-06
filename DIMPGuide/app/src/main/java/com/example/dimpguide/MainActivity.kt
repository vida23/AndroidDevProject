package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signUP.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        signIn.setOnClickListener {
            val intent =  Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }
        continueWithoutLogin.setOnClickListener {
            val intent = Intent(this,ProgrammeYearActivity::class.java)
            startActivity(intent)
        }
    }

}