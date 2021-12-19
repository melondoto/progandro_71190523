package com.revyn.final_71190523

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InsertData : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val namaFilm = findViewById<EditText>(R.id.edtFilm)
        val durasiFilm = findViewById<EditText>(R.id.edtDurasi)
        val genreFilm = findViewById<EditText>(R.id.edtGenre)
        val ratingFilm = findViewById<EditText>(R.id.edtRating)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        btnSimpan.setOnClickListener {

            if(namaFilm.text.toString().isEmpty()){
                namaFilm.error = "Nama film tidak boleh kosong!"
                namaFilm.requestFocus()
                return@setOnClickListener
            }
            if(durasiFilm.text.toString().isEmpty()){
                durasiFilm.error = "Durasi tidak boleh kosong!"
                durasiFilm.requestFocus()
                return@setOnClickListener
            }
            if(genreFilm.text.toString().isEmpty()){
                genreFilm.error = "Genre tidak boleh kosong!"
                genreFilm.requestFocus()
                return@setOnClickListener
            }
            if(ratingFilm.text.toString().isEmpty()){
                ratingFilm.error = "Rating tidak boleh kosong!"
                ratingFilm.requestFocus()
                return@setOnClickListener
            }

            val film = Film(namaFilm.text.toString(), durasiFilm.text.toString(), genreFilm.text.toString(), ratingFilm.text.toString().toInt())
            firestore?.collection("film")?.add(film)
                ?.addOnSuccessListener {
                    Toast.makeText(this, "Film inserted!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }?.addOnFailureListener{
                    Toast.makeText(this, "Film gagal ditambahkan!", Toast.LENGTH_SHORT).show()
                }
        }
    }

    fun btnBatal(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}