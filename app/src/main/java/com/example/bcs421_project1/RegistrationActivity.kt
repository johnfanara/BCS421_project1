package com.example.bcs421_project1
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast


class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val editTextfName = findViewById<EditText>(R.id.editFName)
        val editTextlName = findViewById<EditText>(R.id.editLName)
        val editTextEmail = findViewById<EditText>(R.id.editEmail)
        val editTextPassword = findViewById<EditText>(R.id.editPassword)
        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {
            val fName = editTextfName.text.toString()
            val lName = editTextlName.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            clearErrorMessages()

            if (isValidRegistrationData(fName, lName, email, password)) {
                //if registration is valid user is moved back to login or register screen
                val intent = Intent(this@RegistrationActivity, LoginOrRegisterActivity::class.java)
                startActivity(intent)

                //next few lines save registration data to enable log in
                val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                val newEmail = email
                val newPassword = password
                val newfName = fName
                val newlName = lName

                editor.putString("email", newEmail)
                editor.putString("password", newPassword)
                editor.putString("firstname", newfName)
                editor.putString("lastname", newlName)

                editor.apply()

                showToast("Registration successful!")
            }
            else {
                showToast("Registration unsuccessful! ):")
            }
        }
    }
        //creates messages for successful or unsuccessful registration
        private fun showToast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        //validates registration data
        private fun isValidRegistrationData(
            fName: String,
            lName: String,
            email: String,
            password: String
        ): Boolean {
            val fNameError = findViewById<TextView>(R.id.fNameError)
            val lNameError = findViewById<TextView>(R.id.lNameError)
            val emailError = findViewById<TextView>(R.id.emailError)
            val passwordError = findViewById<TextView>(R.id.passwordError)

            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            var isValid = true

            if (fName.isEmpty()) {
                setErrorMessage(fNameError, "First name cannot be blank")
                isValid = false
            } else if (fName.length < 3 || fName.length > 30) {
                setErrorMessage(fNameError, "First name needs to be between 3 and 30 characters")
                isValid = false
            }
            if (lName.isEmpty()) {
                setErrorMessage(lNameError, "Last name cannot be blank")
                isValid = false
            }
            if (email.isEmpty()) {
                setErrorMessage(emailError, "Email cannot be blank")
                isValid = false
            } else if (!email.matches(emailPattern.toRegex())) {
                setErrorMessage(emailError, "Invalid email")
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
    //sets error messages
    private fun setErrorMessage(textView: TextView, errorMessage: String) {
        textView.text = errorMessage
        textView.visibility = View.VISIBLE
    }
    //removes error messages
    private fun clearErrorMessages() {
        findViewById<TextView>(R.id.fNameError).visibility = View.GONE
        findViewById<TextView>(R.id.lNameError).visibility = View.GONE
        findViewById<TextView>(R.id.emailError).visibility = View.GONE
        findViewById<TextView>(R.id.passwordError).visibility = View.GONE
    }
}


