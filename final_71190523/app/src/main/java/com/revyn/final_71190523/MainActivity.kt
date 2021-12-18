package com.revyn.final_71190523

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnlogout(view: android.view.View) {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}