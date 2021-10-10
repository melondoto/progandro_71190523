package com.example.pertemuan6_71190523

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ThirdFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_third, container, false)
        val btnFrgC = v.findViewById<Button>(R.id.btnFrgC)
        btnFrgC.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        return v
    }
}
