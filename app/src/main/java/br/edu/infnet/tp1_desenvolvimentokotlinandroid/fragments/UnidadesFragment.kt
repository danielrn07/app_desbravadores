package br.edu.infnet.tp1_desenvolvimentokotlinandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.R
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.daos.UnidadeDao
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.databinding.FragmentUnidadesBinding
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Unidade
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.adapters.UnidadeAdapter
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.adapters.UnidadeListener
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.utils.nav
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class UnidadesFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentUnidadesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUnidadesBinding.inflate(inflater, container, false)

        setup()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.unidades.collect { unidades ->
                    adapter.submitList(unidades)
                    binding.rvUnidades.adapter = adapter
                }
            }
        }
    }

    val adapter = UnidadeAdapter(
        object : UnidadeListener {

            override fun onEditClick(unidade: Unidade) {
                viewModel.setSelectedUnidadeId(unidade.id)
                nav(R.id.action_unidadesFragment_to_editarUnidadeFragment)
            }

            override fun onDeleteClick(unidade: Unidade) {
                viewModel.deleteUnidade(unidade)
            }
        }
    )

    private fun setup() {
        setupViews()
        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Unidades");
    }

    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            nav(R.id.action_unidadesFragment_to_novaUnidadeFragment)
        }
    }

    private fun setupRecyclerView() {
        // adapter precisa ser uma variável global para ser acessada por todos os métodos
        binding.rvUnidades.adapter = adapter
        binding.rvUnidades.layoutManager = LinearLayoutManager(
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