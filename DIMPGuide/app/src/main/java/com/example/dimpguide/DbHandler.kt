package com.example.dimpguide
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
class DbHandler(){
    companion object {
        val db = FirebaseFirestore.getInstance()
    }
}