package com.example.homework_3


import android.annotation.SuppressLint
import android.app.Person
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
    private lateinit var newProfile: Profile
    private var age: Int = 0

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


            binding.tvEnterAll.visibility = View.INVISIBLE // all input
            binding.etEmailValid.visibility = View.INVISIBLE // email
            binding.etUserNameValid.visibility = View.INVISIBLE // user name
            binding.tvAgeValid.visibility = View.INVISIBLE // age


            if(email.isBlank() || userName.isBlank() || firstName.isBlank() || lastName.isBlank() || strAge.isBlank()){

                binding.tvEnterAll.visibility = View.VISIBLE

            }else if(!emailValid(email, emailValidatorRegex)){

                binding.etEmailValid.visibility = View.VISIBLE

            }else if(userName.length < 10){

                binding.etUserNameValid.visibility = View.VISIBLE

            }else if(strAge.toInt() < 0){
                binding.tvAgeValid.visibility = View.VISIBLE
            }
            else{
                age = strAge.toInt()
                newProfile = Profile(email, userName, firstName, lastName, age)
                val intent = Intent(this, UserActivity::class.java)
                intent.putExtra("person_data", newProfile)
                startActivity(intent)
            }

        }

        var time = 0L

        binding.btnClear.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    time = System.currentTimeMillis()
                }

                MotionEvent.ACTION_UP -> {
                    if (System.currentTimeMillis() - time >= 2000) {
                        inputClear(binding)
                    }
                }
            }
            true
        }
    }

    private fun emailValid(email: String, regex: Regex): Boolean{
        return regex.matches(email)
    }

    private fun inputClear(binding: ActivityMainBinding){
            binding.etEmail.text = null
            binding.etUserName.text = null
            binding.etFirstName.text = null
            binding.etLastName.text = null
            binding.etnAge.text = null
    }

}