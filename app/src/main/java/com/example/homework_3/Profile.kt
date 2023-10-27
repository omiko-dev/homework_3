package com.example.homework_3

import java.io.Serializable

data class Profile(val email: String, val userName: String, val firstName: String, val lastName: String, val age: Int) : Serializable
