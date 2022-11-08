package br.edu.infnet.tp1_desenvolvimentokotlinandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.R
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.databinding.FragmentHomeBinding
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.utils.nav


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setup()
        return binding.root
    }

    private fun setup() {
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {
            btnUnidades.setOnClickListener {
                nav(R.id.action_homeFragment_to_unidadesFragment)
            }

            btnDesbravadores.setOnClickListener {
                nav(R.id.action_homeFragment_to_desbravadoresFragment)
            }
        }
    }

}

