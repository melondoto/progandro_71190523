package com.revyn.final_71190523

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val register = findViewById<TextView>(R.id.register)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar2)

        register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            if(email.text.toString().isEmpty()){
                email.error = "Email harus diisi!"
                email.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
                email.error = "Masukkan email yang valid!"
                email.requestFocus()
                return@setOnClickListener
            }

            if(password.text.toString().isEmpty()){
                password.error = "Password harus diisi!"
                password.requestFocus()
                return@setOnClickListener
            }

            if(password.text.length < 6){
                password.error = "Panjang password harus >=6"
                password.requestFocus()
                return@setOnClickListener
            }

            if(password.text.length > 12){
                password.error = "Panjang password harus <=12"
                password.requestFocus()
                return@setOnClickListener
            }

            progressBar.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Login gagal! " + task.exception?.message, Toast.LENGTH_SHORT).show()
                        progressBar.visibility = View.INVISIBLE
                    }
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser : FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?){

    }
}