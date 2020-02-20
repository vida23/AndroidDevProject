package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInButton.setOnClickListener {
            val docRef = DbHandler.db.collection("users")
            docRef.whereEqualTo("username",username.text.toString()).whereEqualTo("password",password.text.toString())
                .get()
                .addOnSuccessListener { document ->
                    LoggedInManager.changeLoginState(true)
                    if(LoggedInManager.isLoggedIn){
                        Log.d("NICE",document.documents.toString())
                    }
                    val intent = Intent(this,ProgrammeYearActivity::class.java)
                    startActivity(intent)
                    finish()
                }
        }
    }
}
