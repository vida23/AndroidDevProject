package com.example.dimpguide.ui.home.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dimpguide.R
import com.example.dimpguide.SignInActivity
import com.example.dimpguide.SignupActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 */
class SignOutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (FirebaseAuth.getInstance().currentUser != null){
            FirebaseAuth.getInstance().signOut()
            activity!!.finish()
        }
        else{
            activity!!.finish()
        }



        return null
    }


}
