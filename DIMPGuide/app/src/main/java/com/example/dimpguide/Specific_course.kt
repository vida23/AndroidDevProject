package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.example.dimpguide.DbHandler.Companion.db

class Specific_course : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_course)
        val name = intent.getStringExtra("name")
        val course_id = intent.getStringExtra("course_id")
        findViewById<TextView>(R.id.Course).apply{
            this.text = name
        }

        findViewById<Button>(R.id.CourseMaterialButton)
            .setOnClickListener {
                val intent = Intent(this,CourseMaterialActivity::class.java)
                //Shall put id from the database here when valid
                startActivity(intent)
            }

        findViewById<Button>(R.id.FAQButton)
            .setOnClickListener {
                startActivity(Intent(this,FAQActivity::class.java))
            }

        findViewById<Button>(R.id.AskButton)
            .setOnClickListener {
                startActivity(Intent(this,AskActivity::class.java))
            }

        findViewById<Button>(R.id.GoodToKnowButton)
            .setOnClickListener {
                intent.putExtra("name", name)
                intent.putExtra("course_id", course_id)
                startActivity(Intent(this,GoodToKnowActivity::class.java))
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
