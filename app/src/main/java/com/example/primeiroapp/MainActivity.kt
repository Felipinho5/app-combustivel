package com.example.primeiroapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primeiroapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcular.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.calcular) {
            btnCalcular()
        }
    }

    fun btnCalcular() {
        try {
            val distanciaPercorrida = binding.editDistanciaPercorrida.text.toString().toDouble()
            val preco = binding.editPreco.text.toString().toDouble()
            val autonomia = binding.editAutonomia.text.toString().toDouble()

            val gasto = distanciaPercorrida / autonomia * preco
            binding.gasto.text = "R$%.2f".format(gasto)
        } catch (e: Exception) {
            binding.gasto.text = "Por favor, insira apenas números válidos."
        }
    }
}