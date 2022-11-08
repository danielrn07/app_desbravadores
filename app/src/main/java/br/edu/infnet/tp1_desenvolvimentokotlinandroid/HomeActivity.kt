package br.edu.infnet.tp1_desenvolvimentokotlinandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.databinding.ActivityHomeBinding
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.utils.getNameFromSharedPrefs

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setup()
        setContentView(view)
    }

    private fun setup() {
        binding.tvTitulo.text = "Olá ${getNameFromSharedPrefs()}"
    }
}