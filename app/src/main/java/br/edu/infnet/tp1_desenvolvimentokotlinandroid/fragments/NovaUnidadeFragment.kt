package br.edu.infnet.tp1_desenvolvimentokotlinandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.R
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.databinding.FragmentNovaUnidadeBinding
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Unidade
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.utils.navUp
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.viewmodel.MainViewModel


class NovaUnidadeFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentNovaUnidadeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNovaUnidadeBinding.inflate(inflater, container, false)

        setup()
        return binding.root
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Nova Unidade");
    }

    private fun setupClickListeners() {
        binding.apply {
            fabSaveNovaUnidade.setOnClickListener {
                addNovaUnidade()
            }
        }
    }

    fun addNovaUnidade() {
        if (validateInput()) {
            val novaUnidade = getNovaUnidadeFromInputs()
            viewModel.insertUnidade(novaUnidade)
            navUp()
        }
    }

    fun getNovaUnidadeFromInputs(): Unidade {
        return Unidade(
            nome = binding.inputNome.text.toString()
        )
    }

    fun validateInput(): Boolean {
        var checker = false
        if (!binding.inputNome.text.isNullOrBlank()) {
            checker = true
        } else {
            Toast.makeText(context, "Preencha os campos vazios!", Toast.LENGTH_SHORT).show()
        }
        return checker
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}