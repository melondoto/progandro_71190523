package com.example.pertemuan7_71190523

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView: ImageView = findViewById(R.id.imgContact)
        val txtName = findViewById<TextView>(R.id.txtName)
        val txtNo = findViewById<TextView>(R.id.txtNo)
        val txtLocation = findViewById<TextView>(R.id.txtLocation)
        val txtType = findViewById<TextView>(R.id.txtType)
        val bundle: Bundle? = getIntent().extras
        val image = bundle?.getInt("image")
        if (image != null){
            imageView.setImageResource(image)
        }
        val name = intent.getStringExtra("name")
        val no = intent.getStringExtra("no")
        val location = intent.getStringExtra("location")
        val type = intent.getStringExtra("type")

        txtName.text = "Name: ${name}"
        txtNo.text = "No: ${no}"
        txtLocation.text = "Location: ${location}"
        txtType.text = "Type: ${type} phone"
    }
}