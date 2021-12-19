package com.revyn.final_71190523

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        firestore!!.collection("film").get()
            .addOnSuccessListener {
                var listFilm = ArrayList<Film>()
                listFilm.clear()

                for(doc in it){
                    listFilm.add(Film("${doc.data["film"]}", "${doc.data["durasi"]}", "${doc.data["genre"]}", "${doc.data["rating"]}".toInt()))
                }

                val rcyFilm = findViewById<RecyclerView>(R.id.rcyFilm)
                rcyFilm.layoutManager = LinearLayoutManager(this)
                val adapter = FilmAdapter(listFilm)
                rcyFilm.adapter = adapter
            }
            .addOnFailureListener{
                Log.d("Load Data", "Pengambilan Data Gagal!")
            }
    }

    fun btnlogout(view: android.view.View) {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    fun btnInsert(view: android.view.View) {
        auth = FirebaseAuth.getInstance()
        val intent = Intent(this, InsertData::class.java)
        startActivity(intent)
    }
}