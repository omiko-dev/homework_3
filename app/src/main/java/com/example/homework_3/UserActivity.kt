package com.example.homework_3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_3.databinding.ActivityUserBinding

@Suppress("DEPRECATION")
class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // import Person Data
        val person = intent.getSerializableExtra("person_data") as Profile


        binding.etEmail.text = "Email: ${person.email}"
        binding.etUserName.text = "UserName: ${person.userName}"
        binding.tvFullName.text = "FullName: ${person.firstName} ${person.lastName}"
        binding.etnAge.text = "Age: ${person.age}"


        // Go to |activity_main| layout
        binding.btnAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}