package com.revyn.final_71190523

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditData : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        firestore = FirebaseFirestore.getInstance()

        val namaFilm = getIntent().getStringExtra("film")
        val durasiFilm = getIntent().getStringExtra("durasi")
        val genreFilm = getIntent().getStringExtra("genre")
        val ratingFilm = getIntent().getStringExtra("rating")

        val edtFilm = findViewById<EditText>(R.id.etFilm)
        val edtDurasi = findViewById<EditText>(R.id.etDurasi)
        val edtGenre = findViewById<EditText>(R.id.etGenre)
        val edtRating = findViewById<EditText>(R.id.etRating)
        val btnEdit = findViewById<Button>(R.id.btnSimpanEdit)

        edtFilm.setText(namaFilm)
        edtDurasi.setText(durasiFilm)
        edtGenre.setText(genreFilm)
        edtRating.setText(ratingFilm)

        btnEdit.setOnClickListener {
            val film = edtFilm.text.toString()
            val durasi = edtDurasi.text.toString()
            val genre = edtGenre.text.toString()
            val rating = edtRating.text.toString()

            if(edtFilm.text.toString().isEmpty()){
                edtFilm.error = "Judul film tidak boleh kosong!"
                edtFilm.requestFocus()
                return@setOnClickListener
            }
            if(edtDurasi.text.toString().isEmpty()){
                edtDurasi.error = "Durasi film tidak boleh kosong!"
                edtDurasi.requestFocus()
                return@setOnClickListener
            }
            if(edtGenre.text.toString().isEmpty()){
                edtGenre.error = "Genre film tidak boleh kosong!"
                edtGenre.requestFocus()
                return@setOnClickListener
            }
            if(edtRating.text.toString().isEmpty()){
                edtRating.error = "Rating film tidak boleh kosong!"
                edtRating.requestFocus()
                return@setOnClickListener
            }

            firestore?.collection("film")?.whereEqualTo("film", namaFilm)
                ?.whereEqualTo("durasi", durasiFilm)
                ?.whereEqualTo("genre", genreFilm)?.get()
                ?.addOnSuccessListener {
                    for (doc in it){
                        firestore?.collection("film")?.document(doc.id)
                            ?.update("film", edtFilm.text.toString(), "durasi", edtDurasi.text.toString(), "genre", edtGenre.text.toString(), "rating", edtRating.text.toString().toInt())
                            ?.addOnSuccessListener {
                                Toast.makeText(this, "Film updated!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                            ?.addOnFailureListener {
                                Toast.makeText(this, "Film gagal diupdate!", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                ?.addOnFailureListener {
                    Log.d("Update data", "Data gagal diubah!")
                }
        }
    }
}