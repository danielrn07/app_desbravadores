package br.edu.infnet.tp1_desenvolvimentokotlinandroid.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.R
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.databinding.FragmentDesbravadoresBinding
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Desbravador
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.adapters.DesbravadorAdapter
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.adapters.DesbravadorListener
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.utils.nav
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.viewmodel.MainViewModel
import com.ferfalk.simplesearchview.SimpleSearchView
import kotlinx.coroutines.launch


class DesbravadoresFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentDesbravadoresBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDesbravadoresBinding.inflate(inflater, container, false)

        setup()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.desbravadores.collect { desbravadores ->
                    adapter.submitList(desbravadores)
                    binding.rvDesbravadores.adapter = adapter
                }
            }
        }
    }

    val adapter = DesbravadorAdapter(
        object : DesbravadorListener {

            override fun onEditClick(desbravador: Desbravador) {
                viewModel.setSelectedDesbravadorId(desbravador.id)
                nav(R.id.action_desbravadoresFragment_to_editarDesbravadorFragment)
            }

            override fun onDeleteClick(desbravador: Desbravador) {
                viewModel.deleteDesbravador(desbravador)
            }
        }
    )

    private fun setup() {
        setupClickListeners()
        setupViews()
        setupRecyclerView()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Desbravadores");
    }

    private fun setupClickListeners() {
        binding.apply {
            fabAdd.setOnClickListener {
                nav(R.id.action_desbravadoresFragment_to_novoDesbravadorFragment)
            }
            binding.btnRecuperarPorId.setOnClickListener {
                onGetByIdClick()
            }
        }
    }

    private fun onGetByIdClick() {
        val idString = binding.inputPesquisa.text.toString()
        if (!idString.isNullOrBlank()) {
            val id = idString.toLong()
            lifecycleScope.launch {
                try {
                    val desbravador = viewModel.getDesbravadorById(id)
                    binding.apply {
                        tv1.text = "ID: ${desbravador.id.toString()}"
                        tv2.text = "Nome: ${desbravador.nome}"
                        tv3.text = "ID da Unidade: ${desbravador.unidadeId}"
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Digite o ID!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        // adapter precisa ser uma variável global para ser acessada por todos os métodos
        binding.rvDesbravadores.adapter = adapter
        binding.rvDesbravadores.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
