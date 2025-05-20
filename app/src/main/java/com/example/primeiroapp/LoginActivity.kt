package com.example.primeiroapp

import android.annotation.SuppressLint
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
        binding.btnIncluir.setOnClickListener(this)
        binding.btnLista.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_incluir)
            btnIncluir()
        else if (v.id == R.id.btn_lista)
            btnLista()
    }

    private fun btnIncluir() {
        val db = DBHelper(this, null)

        val name = binding.editNome.text.toString()
        val age = binding.editIdade.text.toString()

        db.addName(name, age)

        Toast.makeText(this, name + " adicionado ao banco.", Toast.LENGTH_LONG).show()

        binding.editNome.text.clear()
        binding.editIdade.text.clear()
    }

    @SuppressLint("Range")
    private fun btnLista() {
        val db = DBHelper(this, null)

        val cursor = db.getName()

        cursor!!.moveToFirst()

        binding.txtNome.text = "Nome\n\n"
        binding.txtIdade.text = "Idade\n\n"

        do {
            binding.txtNome.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL)) + "\n")
            binding.txtIdade.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
        } while (cursor.moveToNext())

        cursor.close()
    }
}