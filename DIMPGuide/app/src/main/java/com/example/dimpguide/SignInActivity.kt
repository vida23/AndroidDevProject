package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInButton.setOnClickListener {
            val email = email_edittext_signin.text.toString()
            val password = password_edittext_signin.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (!task.isSuccessful) return@addOnCompleteListener
                    val intent = Intent(this, ProgrammeYearActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Log.d("signup", "Failed to create:${it.message}")
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
        }

    }
}
