package com.revyn.final_71190523

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter (var listFilm: ArrayList<Film>): RecyclerView.Adapter<FilmAdapter.FilmHolder>(){
    class FilmHolder(val view: View): RecyclerView.ViewHolder(view){
        var film: Film? = null

        fun bind(film: Film){
            this.film = film
            view.findViewById<TextView>(R.id.tvFilm).text = film.film
            view.findViewById<TextView>(R.id.tvDurasi).text = film.durasi
            view.findViewById<TextView>(R.id.tvGenre).text = film.genre
            view.findViewById<TextView>(R.id.tvRating).text = film.rating.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.FilmHolder {
        TODO("Not yet implemented")
        val fh = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmHolder(fh)
    }

    override fun onBindViewHolder(holder: FilmAdapter.FilmHolder, position: Int) {
        TODO("Not yet implemented")
        holder.bind(listFilm[position])
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return listFilm.size
    }
}