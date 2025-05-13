package com.example.primeiroapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primeiroapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_entrar) {
            btnEntrar()
        }
    }

    private fun btnEntrar() {
        val senha = binding.editSenha.text.toString()
        if (senha == ("senha_app")) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else {
            Toast.makeText(this, "Senha inválida", Toast.LENGTH_SHORT).show()
        }
    }
}