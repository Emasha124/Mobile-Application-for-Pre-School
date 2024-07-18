package com.example.preschoolmobileapp
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.preschoolmobileapp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainCreate.setOnClickListener{
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.mainUpdate.setOnClickListener{
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }
        binding.mainDelete.setOnClickListener{
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
        }
        binding.mainView.setOnClickListener{
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
        }

    }
}