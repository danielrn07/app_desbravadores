package br.edu.infnet.tp1_desenvolvimentokotlinandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.R
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.databinding.FragmentEditarDesbravadorBinding
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Desbravador
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Unidade
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.utils.navUp
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.viewmodel.MainViewModel

class EditarDesbravadorFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentEditarDesbravadorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditarDesbravadorBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Editar Desbravador")
    }

    private fun setupClickListeners() {
        binding.apply {
            fabUpdate.setOnClickListener {
                updateDesbravador()
            }
        }
    }

    fun updateDesbravador() {
        try {
            if (validateInput()) {
                val desbravador = getUpdateDesbravadorFromInputs()
                viewModel.updateDesbravador(desbravador)
                navUp()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Preencha os campos vazios!", Toast.LENGTH_SHORT).show()
        }
    }

    fun getUpdateDesbravadorFromInputs(): Desbravador {
        return Desbravador(
            id = viewModel.selectedDesbravadorId.value!!,
            nome = binding.inputNome.text.toString(),
            idade = binding.inputIdade.text.toString().toInt(),
            email = binding.inputEmail.text.toString(),
            unidadeId = binding.inputUnidade.text.toString().toLong()
        )
    }

    fun validateInput(): Boolean {
        var checker = false
        val nome = binding.inputNome.text.toString()
        val email = binding.inputEmail.text.toString()

        if (nome.isNotEmpty() || email.isNotEmpty()) {
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