
package com.example.formvalidation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formvalidation.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fullName = intent.getStringExtra("FULL_NAME") ?: ""
        val email = intent.getStringExtra("EMAIL") ?: ""
        val phone = intent.getStringExtra("PHONE") ?: ""

        binding.txtWelcome.text = "Họ và tên: $fullName"
        binding.txtEmail.text = "Email: $email"
        binding.txtPhone.text = "Số điện thoại: $phone"
    }
}
