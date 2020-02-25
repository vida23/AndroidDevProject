package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInButton.setOnClickListener {
            val docRef = DbHandler.db.collection("users")
            docRef.whereEqualTo("username", username.text.toString())
                .whereEqualTo("password", password.text.toString()).limit(1)
                .get()
                .addOnSuccessListener { document ->
                    if (!document.isEmpty) {
                        LoggedInManager.changeLoginState(true)
                        val intent = Intent(this, ProgrammeYearActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
        }
    }
}
