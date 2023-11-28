package com.samuel.vocatechno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.samuel.vocatechno.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.RBack.setOnClickListener {
            val intent = Intent(this, TampilanAwal::class.java)
            startActivity(intent)
        }
        binding.txtLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()
        binding.RBtn1.setOnClickListener {
            val email = binding.REmail.text.toString()
            val password = binding.RPassword.text.toString()

            if (email.isEmpty()) {
                binding.REmail.error = "Email Harus di Isi"
                binding.REmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.REmail.error = "Email Tidak Valid"
                binding.REmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.RPassword.error = "Password Harus di Isi"
                binding.RPassword.requestFocus()
                return@setOnClickListener
        }
            if (password.length <6){
                binding.RPassword.error = "Password Minimal 6 karakter"
                binding.RPassword.requestFocus()
                return@setOnClickListener 
            }
            RegisterFirebase(email,password)
    }
}

    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
