package com.revyn.final_71190523

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import android.app.Activity





class FilmAdapter (var listFilm: ArrayList<Film>): RecyclerView.Adapter<FilmAdapter.FilmHolder>(){

    class FilmHolder(val view: View): RecyclerView.ViewHolder(view){
        var firestore: FirebaseFirestore? = null
        var film: Film? = null

        fun bind(film: Film){
            firestore = FirebaseFirestore.getInstance()
            this.film = film
            view.findViewById<TextView>(R.id.tvFilm).text = film.film
            view.findViewById<TextView>(R.id.tvDurasi).text = film.durasi
            view.findViewById<TextView>(R.id.tvGenre).text = film.genre
            view.findViewById<TextView>(R.id.tvRating).text = film.rating.toString()

            view.findViewById<Button>(R.id.btnEdit).setOnClickListener {
//                val intent = Intent(view.context, EditData::class.java)
//                intent.putExtra("film", film.film)
//                intent.putExtra("durasi", film.durasi)
//                intent.putExtra("genre", film.genre)
//                intent.putExtra("rating", film.rating.toString())
//                view.context.startActivity(intent)
                showUpdateDialog(film)
            }
        }

        fun showUpdateDialog(film: Film) {
            val builder = AlertDialog.Builder(view.context)
            firestore = FirebaseFirestore.getInstance()

            builder.setTitle("Edit Data")

            val inflater = LayoutInflater.from(view.context)
            val v = inflater.inflate(R.layout.activity_edit, null)

            val etFilm = v.findViewById<EditText>(R.id.etFilm)
            val etDurasi = v.findViewById<EditText>(R.id.etDurasi)
            val etGenre = v.findViewById<EditText>(R.id.etGenre)
            val etRating = v.findViewById<EditText>(R.id.etRating)

            etFilm.setText(film.film)
            etDurasi.setText(film.durasi)
            etGenre.setText(film.genre)
            etRating.setText(film.rating.toString())

            builder.setView(v)

            builder.setPositiveButton("Update"){p0,p1 ->
                val namaFilm = etFilm.text.toString()
                val durasiFilm = etDurasi.text.toString()
                val genreFilm = etGenre.text.toString()
                val ratingFilm = etRating.text.toString()
                if(namaFilm.isEmpty()){
                    etFilm.error = "Judul film tidak boleh kosong!"
                    etFilm.requestFocus()
                    return@setPositiveButton
                }
                if(durasiFilm.isEmpty()){
                    etDurasi.error = "Durasi film tidak boleh kosong!"
                    etDurasi.requestFocus()
                    return@setPositiveButton
                }
                if(genreFilm.isEmpty()){
                    etGenre.error = "Genre film tidak boleh kosong!"
                    etGenre.requestFocus()
                    return@setPositiveButton
                }
                if(ratingFilm.isEmpty()){
                    etRating.error = "Rating film tidak boleh kosong!"
                    etRating.requestFocus()
                    return@setPositiveButton
                }
                firestore!!.collection("film").whereEqualTo("film", film.film)
                    .whereEqualTo("durasi", film.durasi)
                    .whereEqualTo("genre", film.genre)
                    .whereEqualTo("rating", film.rating).get()
                    .addOnSuccessListener {
                        for (doc in it){
                            firestore!!.collection("film").document(doc.id)
                                .update("film", etFilm.text.toString(), "durasi", etDurasi.text.toString(), "genre", etGenre.text.toString(), "rating", etRating.text.toString().toInt())
                                .addOnSuccessListener {
                                    Toast.makeText(view.context, "Film updated!", Toast.LENGTH_SHORT).show()
                                    (view.context as Activity).recreate()
                                }
                        }
                    }
                    .addOnFailureListener {
                        Log.d("Update data", "Data gagal diubah!")
                    }
            }

            builder.setNeutralButton("Delete"){p0,p1 ->
                firestore = FirebaseFirestore.getInstance()

                firestore!!.collection("film").whereEqualTo("film", film.film)
                    .whereEqualTo("durasi", film.durasi)
                    .whereEqualTo("genre", film.genre)
                    .whereEqualTo("rating", film.rating).get()
                    .addOnSuccessListener {
                        for (doc in it){
                            firestore!!.collection("film").document(doc.id)
                                .delete()
                                .addOnSuccessListener {
                                    Toast.makeText(view.context, "Film deleted!", Toast.LENGTH_SHORT).show()
                                    (view.context as Activity).recreate()
                                }
                        }
                    }
                    .addOnFailureListener {
                        Log.d("Delete data", "Data gagal diubah!")
                    }
            }
            builder.setNegativeButton("No"){p0,p1 ->

            }

            val alert = builder.create()
            alert.show()
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.FilmHolder {
        val fh = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmHolder(fh)
    }

    override fun onBindViewHolder(holder: FilmAdapter.FilmHolder, position: Int) {
        holder.bind(listFilm[position])
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}