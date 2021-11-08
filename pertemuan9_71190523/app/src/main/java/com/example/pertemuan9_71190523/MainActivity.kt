package com.example.pertemuan9_71190523

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var sp: SharedPreferences? = null
    var spEdit: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = getSharedPreferences("sp71190523", Context.MODE_PRIVATE)
        spEdit = sp?.edit()

        if(sp?.getBoolean("isLogin", false) == true){
            //LOGIN
            setContentView(R.layout.activity_home)
            val spBahasa = findViewById<Spinner>(R.id.spBahasa)
            val adapter = ArrayAdapter.createFromResource(this, R.array.list_bahasa, R.layout.support_simple_spinner_dropdown_item)
            spBahasa.adapter = adapter
            spBahasa.setSelection(sp!!.getInt("bahasa", 0))
            spBahasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    spEdit?.putInt("bahasa", position)
                    spEdit?.commit()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

            val ukuran = findViewById<EditText>(R.id.edtUkuran)
            ukuran.setText(sp!!.getString("ukuran", "10"))
            ukuran.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    spEdit?.putString("ukuran", s.toString())
                    spEdit?.commit()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }else{
            //BELUM LOGIN
            setContentView(R.layout.activity_main)
            val username = findViewById<EditText>(R.id.username)
            val password = findViewById<EditText>(R.id.password)
            val btnLogin = findViewById<Button>(R.id.btnSubmit)
            btnLogin.setOnClickListener {
                if(username.text.toString() == "admin" && password.text.toString() == "1234"){
                    spEdit?.putBoolean("isLogin", true)
                    spEdit?.commit()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}