package tn.esprit.gamer.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import com.google.android.material.snackbar.Snackbar
import tn.esprit.gamer.R
import tn.esprit.gamer.databinding.ActivityLoginBinding
import tn.esprit.gamer.databinding.ActivitySignUpBinding
import tn.esprit.gamer.utils.MyStatics

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contextView = findViewById<View>(R.id.context_view)

        binding.tiFullName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateFullName()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })

        binding.tiEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateEmail()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })

        binding.tiPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePassword()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })

        binding.tiConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateConfirmPassword()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })

        binding.btnSignUp.setOnClickListener {
            MyStatics.hideKeyboard(this, binding.btnSignUp)

            // Check if the email is already used
            val isEmailAlreadyUsed = isEmailAlreadyUsed(binding.tiEmail.text.toString())

            if (isEmailAlreadyUsed) {
                Snackbar.make(contextView, getString(R.string.msg_email_already_used), Snackbar.LENGTH_SHORT).show()
            } else if (validateFullName() && validateEmail() && validatePassword() && validateConfirmPassword()) {
                // Saving credentials
                saveUserInfo(
                    binding.tiFullName.text.toString(),
                    binding.tiEmail.text.toString(),
                    binding.tiPassword.text.toString()
                )

                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                Snackbar.make(contextView, getString(R.string.msg_error_inputs), Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.btnTermsAndPolicy.setOnClickListener {
            Snackbar.make(contextView, getString(R.string.msg_coming_soon), Snackbar.LENGTH_SHORT).show()
        }

        binding.btnReturn.setOnClickListener {
            finish()
        }
    }
    private fun isEmailAlreadyUsed(email: String): Boolean {
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", null)
        return savedEmail == email
    }
    //saving credentials
    private fun saveUserInfo(fullName: String, email: String, password: String) {
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("full_name", fullName)
        editor.putString("email", email)
        editor.putString("password", password)

        editor.apply()
    }

    private fun validateFullName(): Boolean {
        binding.tiFullNameLayout.isErrorEnabled = false

        if (binding.tiFullName.text.toString().isEmpty()) {
            binding.tiFullNameLayout.error = getString(R.string.msg_must_not_be_empty)
            binding.tiFullName.requestFocus()
            return false
        }else{
            binding.tiFullNameLayout.isErrorEnabled = false
        }

        if (binding.tiFullName.text.toString().length < 6) {
            binding.tiFullNameLayout.error = getString(R.string.msg_check_your_characters)
            binding.tiFullName.requestFocus()
            return false
        }else{
            binding.tiFullNameLayout.isErrorEnabled = false
        }

        return true
    }

    private fun validateEmail(): Boolean {
        binding.tiEmailLayout.isErrorEnabled = false

        if (binding.tiEmail.text.toString().isEmpty()) {
            binding.tiEmailLayout.error = getString(R.string.msg_must_not_be_empty)
            binding.tiEmail.requestFocus()
            return false
        }else{
            binding.tiEmailLayout.isErrorEnabled = false
        }
        val existingEmail = checkIfEmailExists(binding.tiEmail.text.toString())


        if (existingEmail) {
            binding.tiEmail.requestFocus()
            return false
        } else {
            binding.tiEmailLayout.isErrorEnabled = false
        }

        return true
    }
    private fun checkIfEmailExists(email: String): Boolean {
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", null)

        return savedEmail == email
    }

    private fun validatePassword(): Boolean {
        binding.tiPasswordLayout.isErrorEnabled = false

        if (binding.tiPassword.text.toString().isEmpty()) {
            binding.tiPasswordLayout.error = getString(R.string.msg_must_not_be_empty)
            binding.tiPassword.requestFocus()
            return false
        }else{
            binding.tiPasswordLayout.isErrorEnabled = false
        }

        if (binding.tiPassword.text.toString().length < 6) {
            binding.tiPasswordLayout.error = getString(R.string.msg_check_your_characters)
            binding.tiPassword.requestFocus()
            return false
        }else{
            binding.tiPasswordLayout.isErrorEnabled = false
        }

        return true
    }

    private fun validateConfirmPassword(): Boolean {
        binding.tiConfirmPasswordLayout.isErrorEnabled = false

        if (binding.tiConfirmPassword.text.toString().isEmpty()) {
            binding.tiConfirmPasswordLayout.error = getString(R.string.msg_must_not_be_empty)
            binding.tiConfirmPassword.requestFocus()
            return false
        }else{
            binding.tiConfirmPasswordLayout.isErrorEnabled = false
        }

        if (binding.tiConfirmPassword.text.toString().length < 6) {
            binding.tiConfirmPasswordLayout.error = getString(R.string.msg_check_your_characters)
            binding.tiConfirmPassword.requestFocus()
            return false
        }else{
            binding.tiConfirmPasswordLayout.isErrorEnabled = false
        }

        if (!binding.tiConfirmPassword.text.toString().equals(binding.tiPassword.text.toString())) {
            binding.tiConfirmPasswordLayout.error = getString(R.string.msg_check_your_confirm_Password)
            binding.tiPasswordLayout.error = getString(R.string.msg_check_your_confirm_Password)
            binding.tiConfirmPassword.requestFocus()
            return false
        }else{
            binding.tiConfirmPasswordLayout.isErrorEnabled = false
            binding.tiPasswordLayout.isErrorEnabled = false
        }

        return true
    }
}