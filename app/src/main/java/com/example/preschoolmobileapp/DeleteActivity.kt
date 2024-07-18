package com.example.preschoolmobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.preschoolmobileapp.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val name = binding.deleteName.text.toString()
            if (name.isNotEmpty()) {
                deleteData(name)
            } else {
                Toast.makeText(this, "Please enter the name", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backbutton3.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }

    private fun deleteData(name: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(name).removeValue().addOnSuccessListener {
            binding.deleteName.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }


    }



}
