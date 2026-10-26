
package com.example.formvalidation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.formvalidation.databinding.ActivityMainBinding
import com.example.formvalidation.model.User
import com.example.formvalidation.validator.FormValidator
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTextListeners()

        binding.btnSubmit.setOnClickListener {
            registerUser()
        }

        binding.btnCancel.setOnClickListener {
            clearForm()
        }
    }

    private fun setupTextListeners() {
        binding.edtName.doAfterTextChanged {
            binding.tilName.error = null
        }

        binding.edtEmail.doAfterTextChanged {
            binding.tilEmail.error = null
        }

        binding.edtPassword.doAfterTextChanged {
            binding.tilPassword.error = null
        }

        binding.edtPhone.doAfterTextChanged {
            binding.tilPhone.error = null
        }
    }

    private fun registerUser() {
        val fullName = binding.edtName.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString()
        val phone = binding.edtPhone.text.toString().trim()

        var isValid = true

        binding.tilName.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilPhone.error = null

        if (!FormValidator.isValidName(fullName)) {
            binding.tilName.error = "Họ tên phải có ít nhất 3 ký tự"
            isValid = false
        }

        if (!FormValidator.isValidEmail(email)) {
            binding.tilEmail.error = "Email không hợp lệ"
            isValid = false
        }

        if (!FormValidator.isValidPassword(password)) {
            binding.tilPassword.error = "Mật khẩu phải có ít nhất 6 ký tự"
            isValid = false
        }

        if (!FormValidator.isValidPhone(phone)) {
            binding.tilPhone.error = "Số điện thoại không hợp lệ"
            isValid = false
        }

        if (!binding.cbTerms.isChecked) {
            isValid = false
            Snackbar.make(
                binding.root,
                "Bạn cần đồng ý với các điều khoản",
                Snackbar.LENGTH_LONG
            ).show()
        }

        if (!isValid) {
            if (binding.cbTerms.isChecked) {
                Snackbar.make(
                    binding.root,
                    "Vui lòng kiểm tra lại thông tin",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            return
        }

        val user = User(
            fullName = fullName,
            email = email,
            password = password,
            phone = phone
        )

        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("FULL_NAME", user.fullName)
        intent.putExtra("EMAIL", user.email)
        intent.putExtra("PHONE", user.phone)

        startActivity(intent)
    }

    private fun clearForm() {
        binding.edtName.text?.clear()
        binding.edtEmail.text?.clear()
        binding.edtPassword.text?.clear()
        binding.edtPhone.text?.clear()

        binding.cbTerms.isChecked = false

        binding.tilName.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilPhone.error = null

        binding.edtName.requestFocus()

        Snackbar.make(
            binding.root,
            "Đã xóa toàn bộ thông tin",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
