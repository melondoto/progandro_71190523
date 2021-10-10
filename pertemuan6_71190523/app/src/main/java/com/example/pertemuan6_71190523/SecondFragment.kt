package com.example.pertemuan6_71190523

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_second, container, false)
        val btnFrgB = v.findViewById<Button>(R.id.btnFrgB)
        btnFrgB.setOnClickListener {
            val intent = Intent(context, ThirdActivity::class.java)
            startActivity(intent)
        }
        return v
    }
}
