package com.example.preschoolmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.preschoolmobileapp.databinding.ActivityReadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchname : String = binding.searchName.text.toString()
            if  (searchname.isNotEmpty()){
                readData(searchname)
            }
            else{
                Toast.makeText(this,"Please enter the student name",Toast.LENGTH_SHORT).show()
            }
        }

        binding.backbutton4.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }

    private fun readData(name: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(name).get().addOnSuccessListener {
            dataSnapshot ->
            if (dataSnapshot.exists()) {
                val phoneNumber = dataSnapshot.child("phone").value
                val guardian = dataSnapshot.child("guardian").value

                Toast.makeText(this, "Results Found", Toast.LENGTH_SHORT).show()
                binding.searchName.text.clear()
                binding.readphone.text = phoneNumber.toString()
                binding.readguardian.text = guardian.toString()
            }
            else {
                Toast.makeText(this, "Student name does not exist", Toast.LENGTH_SHORT).show()
            }
        }
            .addOnFailureListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }


}