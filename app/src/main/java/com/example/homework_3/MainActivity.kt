package com.example.homework_3


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var email: String
    private lateinit var userName: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private var age: Int = 0
    private lateinit var newProfile: Profile


    private val emailValidatorRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,5}\$")

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            email = binding.etEmail.text.toString()
            userName = binding.etUserName.text.toString()
            firstName = binding.etFirstName.text.toString()
            lastName = binding.etLastName.text.toString()
            val strAge = binding.etnAge.text.toString()

            // Invisible All Validator Text
            binding.tvAllValid.visibility = View.INVISIBLE // all input
            binding.tvEmailValid.visibility = View.INVISIBLE // email
            binding.tvUserNameValid.visibility = View.INVISIBLE // user name
            binding.tvAgeValid.visibility = View.INVISIBLE // age

            if(
                email.isBlank() ||
                userName.isBlank() ||
                firstName.isBlank() ||
                lastName.isBlank() ||
                strAge.isBlank()
                ){  // Another Field Are Blank
                binding.tvAllValid.visibility = View.VISIBLE
            }
            else if(!isEmailValid(email, emailValidatorRegex)){ // Email validator
                binding.tvEmailValid.visibility = View.VISIBLE
            }
            else if(userName.length < 10){ // Username validator
                binding.tvUserNameValid.visibility = View.VISIBLE
            }
            else if(strAge.toInt() < 0){ // Age Validator
                binding.tvAgeValid.visibility = View.VISIBLE
            }
            else{  // every field are correct
                age = strAge.toInt()
                newProfile = Profile(email, userName, firstName, lastName, age)

                // Go to |activity_user| layout
                val intent = Intent(this, UserActivity::class.java)
                intent.putExtra("person_data", newProfile)  // import Person Class Data
                startActivity(intent)
            }

        }

        // clear button loader logic
        var time = 0L
        binding.btnClear.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    time = System.currentTimeMillis()
                }

                MotionEvent.ACTION_UP -> {
                    if (System.currentTimeMillis() - time >= 2000) {
                        inputClearer(binding)
                    }
                }
            }
            true
        }
    }

    private fun isEmailValid(email: String, regex: Regex): Boolean{
        return regex.matches(email)
    }

    private fun inputClearer(binding: ActivityMainBinding){
            binding.etEmail.text = null
            binding.etUserName.text = null
            binding.etFirstName.text = null
            binding.etLastName.text = null
            binding.etnAge.text = null
    }

}