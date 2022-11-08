package br.edu.infnet.tp1_desenvolvimentokotlinandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.fragment.app.activityViewModels
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.databinding.FragmentEditarUnidadeBinding
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Unidade
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.utils.navUp
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.viewmodel.MainViewModel

class EditarUnidadeFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentEditarUnidadeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditarUnidadeBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Editar Unidade")
    }

    private fun setupClickListeners() {
        binding.apply {
            fabUpdate.setOnClickListener {
                updateUnidade()
            }
        }
    }

    fun updateUnidade() {
        if (validateInput()) {
            val unidade = getUpdateUnidadeFromInputs()
            viewModel.updateUnidade(unidade)
            navUp()
        }
    }

    fun getUpdateUnidadeFromInputs(): Unidade {
        return Unidade(
            id = viewModel.selectedUnidadeId.value!!,
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