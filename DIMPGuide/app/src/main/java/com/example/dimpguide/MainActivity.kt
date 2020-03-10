package com.example.dimpguide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    companion object{
        lateinit var MAINACTIVITY: Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MAINACTIVITY = this
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
            val intent = Intent(this,ActingMainActivity::class.java)
            startActivity(intent)
        }

    }

    override public fun onStart() {
        super.onStart()
        //Check if logged in, redirect to "programmeY..."
        if (FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(this,ActingMainActivity::class.java))
        }
    }
}
