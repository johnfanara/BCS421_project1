package com.example.bcs421_project1

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OnLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onlogin)

        //gets saved first and last name from registration and displays a greeting
        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        val savedFName = sharedPreferences.getString("firstname", "")
        val savedLName = sharedPreferences.getString("lastname", "")

        val greeting = "Hello $savedFName $savedLName! You just got crawed!"
        val onLoginGreeting = findViewById<TextView>(R.id.onLoginGreeting)
        onLoginGreeting.text = greeting
    }
}