package br.edu.infnet.tp1_desenvolvimentokotlinandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.databinding.ActivityMainBinding
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.utils.saveNameToSharedPrefs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setup()
    }

    private fun setup() {
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnAvancar.setOnClickListener {
            if (binding.inputNome.text.toString().isNotBlank()) {
                val intent = Intent(this, HomeActivity::class.java)
                val nome = binding.inputNome.text.toString()
                saveNameToSharedPrefs(nome)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Preencha o campo vazio.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}