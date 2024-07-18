package com.example.preschoolmobileapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import com.example.preschoolmobileapp.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.referencePhone.filters = arrayOf(InputFilter.LengthFilter(10))


        binding.updateButton.setOnClickListener {
            val updateName = binding.updateName.text.toString()
            val updatePhone = binding.referencePhone.text.toString()
            val updateGuardian = binding.updateguardian.text.toString()

            if (updatePhone.length != 10) {
                Toast.makeText(this, "Phone number must be 10 digits", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            updateData(updateName, updatePhone, updateGuardian)
        }

        binding.backbutton2.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }

    private fun updateData(name: String, phone: String, guardian: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf(
            "name" to name,
            "phone" to phone,
            "guardian" to guardian,

        )


        database.child(name).updateChildren(user).addOnSuccessListener {
            binding.updateName.text.clear()
            binding.referencePhone.text.clear()
            binding.updateguardian.text.clear()

            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
        }


    }
}
