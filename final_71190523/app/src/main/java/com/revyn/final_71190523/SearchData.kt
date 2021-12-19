package com.revyn.final_71190523

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SearchData : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        firestore = FirebaseFirestore.getInstance()

        val cari = getIntent().getStringExtra("cari")
        val txtCari = findViewById<TextView>(R.id.txtFilter)

        txtCari.setText("Cari berdasarkan kata: " + cari.toString())

        firestore!!.collection("film").whereGreaterThanOrEqualTo("film", cari.toString())
            .whereLessThanOrEqualTo("film", cari.toString() + "~")
            .orderBy("film", Query.Direction.ASCENDING).get()
            .addOnSuccessListener {
                var listFilmSearch = ArrayList<Film>()
                listFilmSearch.clear()

                for(doc in it){
                    listFilmSearch.add(Film("${doc.data["film"]}", "${doc.data["durasi"]}", "${doc.data["genre"]}", "${doc.data["rating"]}".toInt()))
                }

                val rcySearch = findViewById<RecyclerView>(R.id.rcySearch)
                rcySearch.layoutManager = LinearLayoutManager(this)
                val adapter = FilmAdapter(listFilmSearch)
                rcySearch.adapter = adapter
            }
            .addOnFailureListener{
                Log.d("Load Data", "Pengambilan Data Gagal!")
            }
    }

    fun btnKembali(view: android.view.View) {
        auth = FirebaseAuth.getInstance()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}