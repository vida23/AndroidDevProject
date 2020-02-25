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

    private lateinit var auth: FirebaseAuth

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
            val intent = Intent(this,ProgrammeYearActivity::class.java)
            startActivity(intent)
        }

        ProgrammeYearActivity.timesBackButtonPressed = 0

        if (LoggedInManager.isLoggedIn == true) {
            Log.i("SignedIn", "User is signed in")
        } else {
            // No user is signed in
            Log.i("SignedIn", "User is NOT signed in")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(LoggedInManager.isLoggedIn){
            menuInflater.inflate(R.menu.app_bar_menu,menu)
            return true

        }else
            return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title == getString(R.string.sign_out)){
            LoggedInManager.changeLoginState(false)
            item.title = getString(R.string.sign_in)
            return true
        }else if(item.title == getString(R.string.sign_in)){
            startActivity(Intent(this,SignInActivity::class.java))
            return true
        }
        return false
    }
}
