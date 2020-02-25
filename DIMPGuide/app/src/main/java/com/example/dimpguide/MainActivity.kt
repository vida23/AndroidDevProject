package com.example.dimpguide

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
            val intent = Intent(this,ProgrammeYearActivity::class.java)
            startActivity(intent)
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
