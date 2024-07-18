package com.example.preschoolmobileapp

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.preschoolmobileapp.databinding.ActivityUploadBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Users")

        binding.uploadPhone.filters = arrayOf(InputFilter.LengthFilter(10))

        binding.saveButton.setOnClickListener {
            val name = binding.uploadName.text.toString()
            val guardian = binding.uploadGuardian.text.toString()
            val phone = binding.uploadPhone.text.toString()

            if (phone.length != 10) {
                Toast.makeText(this@UploadActivity, "Phone number must be 10 digits", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            database.child(name).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Toast.makeText(this@UploadActivity, "Name already exists", Toast.LENGTH_SHORT).show()
                    }

                    else {
                        val newUser = User1(name, guardian, phone)
                        database.child(name).setValue(newUser)
                            .addOnSuccessListener {
                                binding.uploadName.text.clear()
                                binding.uploadGuardian.text.clear()
                                binding.uploadPhone.text.clear()
                                Toast.makeText(this@UploadActivity, "Saved", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@UploadActivity, MainActivity2::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this@UploadActivity, "Failed", Toast.LENGTH_SHORT).show()
                            }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@UploadActivity, "Database Error", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.backbutton1.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }



}
