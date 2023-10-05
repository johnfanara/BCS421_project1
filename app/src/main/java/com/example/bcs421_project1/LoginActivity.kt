package com.example.bcs421_project1
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextEmail = findViewById<EditText>(R.id.loginEmail)
        val editTextPassword = findViewById<EditText>(R.id.loginPassword)
        val btn = findViewById<Button>(R.id.loginBtn)

        btn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            clearErrorMessages()

            // if valid login user is moved to the onLogin activity
            if (isValidLoginData(email, password)) {
                val intent = Intent(this@LoginActivity, OnLoginActivity::class.java)
                showToast("Login successful")
                startActivity(intent)
                } else {
                    showToast("Login unsuccessful")
                }
            }
        }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    //validates the login data
    private fun isValidLoginData(
        email: String,
        password: String
    ): Boolean {
        val emailError = findViewById<TextView>(R.id.emailError)
        val passwordError = findViewById<TextView>(R.id.passwordError)

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        var isValid = true

        if (email.isEmpty()) {
            setErrorMessage(emailError, "Email cannot be empty")
            isValid = false
        } else if (!email.matches(emailPattern.toRegex())) {
            setErrorMessage(emailError, "Invalid emai3l")
            isValid = false
        }
        if (password.isEmpty()) {
            setErrorMessage(passwordError, "Password cannot be blank")
            isValid = false
        } else if (password.length < 6) {
            setErrorMessage(passwordError, "Password must be at least 6 characters")
            isValid = false
        }
        return isValid
    }

    private fun setErrorMessage(textView: TextView, errorMessage: String) {
        textView.text = errorMessage
        textView.visibility = View.VISIBLE
    }

    private fun clearErrorMessages() {
        findViewById<TextView>(R.id.emailError).visibility = View.GONE
        findViewById<TextView>(R.id.passwordError).visibility = View.GONE
    }
}