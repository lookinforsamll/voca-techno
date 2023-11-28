package com.samuel.vocatechno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samuel.vocatechno.databinding.ActivityAdminnBinding

class AdminnActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminnBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminnBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.vieworderr.setOnClickListener {
            val intent = Intent(this, vieworderActivity::class.java)
            startActivity(intent)
        }
        binding.infoo.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
    }
}
