package com.example.homeworkthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.homeworkthree.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val saveButton = binding.saveButton
        val emailInput = binding.emailInput
        val userNameInput = binding.userNameInput
        val firstNameInput = binding.firstNameInput
        val lastNameInput = binding.lastNameInput
        val ageInput = binding.ageInput
        val clearButton = binding.clearButton
        val againButton = binding.againButton
        val editTextSection = binding.editTextSection
        val resultSection = binding.resultSection
        val resultUserName = binding.inputUserNameTextView
        val resultFullName = binding.userFullName
        val resultEmail = binding.userEmail
        val resultAge = binding.ageUserInput

        saveButton.setOnClickListener {
            try {
                user = UserData(
                    firstName = firstNameInput.text.toString(),
                    lastName = lastNameInput.text.toString(),
                    userName = userNameInput.text.toString(),
                    email = emailInput.text.toString(),
                    age = ageInput.text.toString().toInt()
                )

                if (userNameInput.text.toString().length < 10) {
                    Toast.makeText(this, "UserName must be at least 10 symbols long", Toast.LENGTH_SHORT).show()
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
                    Toast.makeText(this, "Type in valid email address", Toast.LENGTH_SHORT).show()
                }

                if (Patterns.EMAIL_ADDRESS.matcher(user.email).matches() &&
                    user.userName.length > 10 &&
                    user.firstName != "" &&
                    user.lastName != "" ) {

                    changeVisibility(againButton, true)
                    changeVisibility(saveButton, false)
                    changeVisibility(clearButton, false)
                    changeVisibility(editTextSection, false)
                    changeVisibility(resultSection, true)

                    val userFullName = user.userName + " " + user.lastName

                    resultFullName.text = userFullName
                    resultEmail.text = user.email
                    resultUserName.text = user.userName
                    resultAge.text = user.age.toString()

                } else if (user.firstName == "" || user.lastName == "" ) {
                    Toast.makeText(this, "Fill first name and last name", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(this, "Type in all mandatory fields", Toast.LENGTH_SHORT).show()
            }
        }

        clearButton.setOnLongClickListener {
            fun onLongClick(): Boolean {
                return true
            }

            firstNameInput.text?.clear()
            lastNameInput.text?.clear()
            emailInput.text?.clear()
            userNameInput.text?.clear()
            ageInput.text?.clear()
            onLongClick()
        }

        againButton.setOnClickListener {
            changeVisibility(againButton, false)
            changeVisibility(saveButton, true)
            changeVisibility(clearButton, true)
            changeVisibility(editTextSection, true)
            changeVisibility(resultSection, false)
        }
    }

    private fun changeVisibility(v: View, isVisible: Boolean) {
        if (isVisible)
            v.visibility = View.VISIBLE
        else {
            v.visibility = View.GONE
        }
    }
}