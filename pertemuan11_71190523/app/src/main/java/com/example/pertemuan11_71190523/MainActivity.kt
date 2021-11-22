package com.example.pertemuan11_71190523

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()

        val edtNim = findViewById<EditText>(R.id.edtNim)
        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtIpk = findViewById<EditText>(R.id.edtIpk)
        val rg1 = findViewById<RadioGroup>(R.id.rg1)
        val rg2 = findViewById<RadioGroup>(R.id.rg2)
        val radioAsc = findViewById<RadioButton>(R.id.radioAsc)
        val radioDesc = findViewById<RadioButton>(R.id.radioDesc)
        val radioNim = findViewById<RadioButton>(R.id.radioNim)
        val radioNama = findViewById<RadioButton>(R.id.radioNama)
        val radioIpk = findViewById<RadioButton>(R.id.radioIpk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCari = findViewById<Button>(R.id.btnCari)
        val btnCariData = findViewById<Button>(R.id.btnCariData)
        val txtOutput = findViewById<TextView>(R.id.txtOutput)


        btnSimpan.setOnClickListener {
            val mahasiswa = Mahasiswa(edtNim.text.toString(), edtNama.text.toString(), edtIpk.text.toString().toInt())
            edtNim.setText("")
            edtNama.setText("")
            edtIpk.setText("")
            firestore?.collection("mahasiswa")?.add(mahasiswa)
        }

        btnCari.setOnClickListener {
            if(radioAsc.isChecked()){
                if(radioNim.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("nim", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtOutput.setText(output)
                        }
                }else if(radioNama.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("nama", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtOutput.setText(output)
                        }
                }else if(radioIpk.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("ipk", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtOutput.setText(output)
                        }
                }
            }else if(radioDesc.isChecked()){
                if(radioNim.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("nim", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtOutput.setText(output)
                        }
                }else if(radioNama.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("nama", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtOutput.setText(output)
                        }
                }else if(radioIpk.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("ipk", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtOutput.setText(output)
                        }
                }
            }
            rg1.clearCheck()
            rg2.clearCheck()
        }

        btnCariData.setOnClickListener {
            if(edtNim.text.toString() != "" && edtNim.text.toString() != null){
                firestore?.collection("mahasiswa")?.whereEqualTo("nim", edtNim.text.toString())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for(doc in docs){
                            output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                        }
                        txtOutput.setText(output)
                    }
            }else if(edtNama.text.toString() != "" && edtNama.text.toString() != null){
                firestore?.collection("mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for(doc in docs){
                            output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                        }
                        txtOutput.setText(output)
                    }
            }else if(edtIpk.text.toString() != "" && edtNama.text.toString() != null){
                firestore?.collection("mahasiswa")?.whereEqualTo("ipk", edtIpk.text.toString())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for(doc in docs){
                            output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                        }
                        txtOutput.setText(output)
                    }
            }
        }
    }
}