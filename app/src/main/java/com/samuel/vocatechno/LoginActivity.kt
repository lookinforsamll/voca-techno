package com.samuel.vocatechno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.samuel.vocatechno.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        binding.LBack.setOnClickListener {
            val intent = Intent(this, TampilanAwal::class.java)
            startActivity(intent)
        }
        binding.txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.LBtn1.setOnClickListener {
            val email = binding.LEmail.text.toString()
            val password = binding.LPassword.text.toString()

            if (email.isEmpty()) {
                binding.LEmail.error = "Email Harus di Isi"
                binding.LEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.LEmail.error = "Email Tidak Valid"
                binding.LEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.LPassword.error = "Password Harus di Isi"
                binding.LPassword.requestFocus()
                return@setOnClickListener
            }
            LoginFirebase(email, password)
        }
    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Sebagai $email", Toast.LENGTH_SHORT).show()
                    saveLoginStatus(email)
                    navigateToCorrectPage(email)
                } else {
                    if (email == "admin@admin.com" && password == "bukanadmin") {
                        Toast.makeText(this, "Login Sebagai Admin.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, AdminnActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Email atau Password Salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun saveLoginStatus(email: String, isAdmin: Boolean = false) {
        val sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.putString("userEmail", email)
        editor.putBoolean("isAdmin", isAdmin)
        editor.apply()
    }

    private fun navigateToCorrectPage(email: String) {
        val sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE)
        val isAdmin = sharedPreferences.getBoolean("isAdmin", false)

        val intent = if (isAdmin) {
            Intent(this, AdminnActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }
        intent.putExtra("userEmail", email)
        startActivity(intent)
    }
}