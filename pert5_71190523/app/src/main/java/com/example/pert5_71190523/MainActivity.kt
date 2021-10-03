package com.example.pert5_71190523

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val btnKirim = findViewById<Button>(R.id.btnKirim)

        btnKirim.setOnClickListener{
            var userName = username.text.toString()
            var pass = password.text.toString()

            if(pass.equals("1234")){
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("username", username.text.toString())
                intent.putExtra("password", password.text.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this, "Password salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}