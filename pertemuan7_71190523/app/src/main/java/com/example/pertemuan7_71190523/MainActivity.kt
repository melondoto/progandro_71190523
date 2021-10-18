package com.example.pertemuan7_71190523

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnContactClickListener {
    val listContact = arrayListOf<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listContact.add(Contact("Revyn Pradana Putra", "081395315404", "Indonesia", "Mobile", R.mipmap.revyn))
        listContact.add(Contact("Kim Jong Un", "82111222333", "Korea Selatan", "Work", R.mipmap.kim))
        listContact.add(Contact("Cu Pat Kai", "0129834765", "Gunung Hwakuo", "Mobile", R.mipmap.cupatkai))

        val rcyContact = findViewById<RecyclerView>(R.id.rcyContact)
        rcyContact.layoutManager = LinearLayoutManager(this)
        val adapter = ContactAdapter(listContact, this, this)
        rcyContact.adapter = adapter
    }

    override fun onContactItemClicked(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("image", listContact[position].image)
        intent.putExtra("name", listContact[position].name)
        intent.putExtra("no", listContact[position].no)
        intent.putExtra("location", listContact[position].location)
        intent.putExtra("type", listContact[position].type)
        startActivity(intent)
    }
}