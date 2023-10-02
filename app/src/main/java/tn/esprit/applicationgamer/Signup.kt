package tn.esprit.applicationgamer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import tn.esprit.applicationgamer.databinding.ActivityMainBinding
import tn.esprit.applicationgamer.databinding.ActivitySignupBinding

class Signup : AppCompatActivity() {
    private lateinit var binding : ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        binding = ActivitySignupBinding.inflate(layoutInflater,null,false)
        setContentView(binding.root)

        binding.emailtext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val email = s?.toString()
                if (email != null) {
                    emailValidate(email)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s?.toString()
                if (email != null) {
                    emailValidate(email)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                val email = s?.toString()
                if (email != null) {
                    emailValidate(email)
                }
            }
        })

        binding.passwordtext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val password = s?.toString()
                if (password != null) {
                    passwordValidation(password)                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s?.toString()
                if (password != null) {
                    passwordValidation(password)                }
            }

            override fun afterTextChanged(s: Editable?) {
                val password = s?.toString()
                if (password != null) {
                    passwordValidation(password)
                }
            }
        })

        binding.nametext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val name = s?.toString()
                if (name != null) {
                    namedValidation(name)                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = s?.toString()
                if (name != null) {
                    namedValidation(name)                }
            }

            override fun afterTextChanged(s: Editable?) {
                val name = s?.toString()
                if (name != null) {
                    namedValidation(name)
                }
            }
        })

    }

    private fun emailValidate(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@esprit\\.tn\$".toRegex()
        if(email.matches(emailRegex) && email.isNotEmpty()) {
            binding.email1.helperText = ""
            return true
        }else if(email.isEmpty()){
            binding.email1.helperText = "Must not be empty !"
            binding.email1.setPlaceholderText("@esprit.tn")
            return false

        }
        else{
            binding.email1.helperText = "Not the right format !"
            return false
        }
    }

    private fun  namedValidation(name : String): Boolean {
        val name = binding.nametext.text.toString()
        if(name.isNotEmpty()) {
            return true
        }else{
            binding.email1.helperText = "Must not be empty !"
            return false
        }
    }

    private fun passwordValidation(pwd :String) :Boolean {
        if (pwd.length < 8){
            binding.password.helperText = "Minimum 8 characters !"
            return false
        }
        if(pwd.isEmpty()){
            binding.password.helperText = " Must not be empty !"
        }
        if(pwd.isNotEmpty()){
            binding.password.helperText = ""

        }
        if (pwd.filter { it.isDigit() }.firstOrNull() == null) {
            binding.password.helperText = " Must contain Numbers"
            return false
        }
        if (pwd.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null){
            binding.password.helperText = " Must contain UpperCase"
            return false
        }
        if (pwd.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) {
            binding.password.helperText = " Must contain LowerCase"
            return false
        }
        return true
    }

}