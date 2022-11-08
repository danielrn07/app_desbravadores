package br.edu.infnet.tp1_desenvolvimentokotlinandroid.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Desbravador
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Unidade
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.repositories.ClubeDesbravadoresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val TAG = "Desbravador"

    private val repository = ClubeDesbravadoresRepository.get()

    private val _unidades: MutableStateFlow<List<Unidade>> = MutableStateFlow(emptyList())
    val unidades: StateFlow<List<Unidade>>
        get() = _unidades.asStateFlow()

    private val _desbravadores: MutableStateFlow<List<Desbravador>> = MutableStateFlow(emptyList())
    val desbravadores: StateFlow<List<Desbravador>>
        get() = _desbravadores.asStateFlow()

    // Guarda a unidade selecionada para edição
    private val _selectedUnidadeId = MutableLiveData<Long>(0L)
    val selectedUnidadeId: LiveData<Long> = _selectedUnidadeId
    fun setSelectedUnidadeId(value: Long) {
        _selectedUnidadeId.setValue(value)
    }

    // Guarda o desbravador selecionado para edição
    private val _selectedDesbravadorId = MutableLiveData<Long>(0L)
    val selectedDesbravadorId: LiveData<Long> = _selectedDesbravadorId
    fun setSelectedDesbravadorId(value: Long) {
        _selectedDesbravadorId.setValue(value)
    }




    ///////////////////////////////
    // CRUD unidade //////////////
    fun insertUnidade(unidade: Unidade) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUnidade(unidade)
        }
    }

    fun updateUnidade(unidade: Unidade) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUnidade(unidade)
        }
    }

    fun deleteUnidade(unidade: Unidade) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUnidade(unidade)
        }
    }

    // Se usa async pois o launch não tem retorno
    suspend fun getUnidadeById(id: Long): Unidade {
        val unidade = viewModelScope.async(Dispatchers.IO) {
            return@async repository.getUnidadeById(id)
        }
        return unidade.await()
    }

    ///////////////////////////////
    // CRUD desbravador //////////

    fun insertDesbravador(desbravador: Desbravador) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDesbravador(desbravador)
        }
    }

    fun updateDesbravador(desbravador: Desbravador) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDesbravador(desbravador)
        }
    }

    fun deleteDesbravador(desbravador: Desbravador) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDesbravador(desbravador)
        }
    }

    // Recebe desbravadores do banco de dados como StateFlow e permanece a última pesquisa feita
    private val _desbravadoresByName: MutableStateFlow<List<Desbravador>> = MutableStateFlow(emptyList())
    val desbravadoresByName: StateFlow<List<Desbravador>>
        get() = _desbravadoresByName.asStateFlow()

    // Pesquisando com "%${input}%" vai retornar os que contém input no nome
    fun collectDesbravadoresByName(input: String) {
        viewModelScope.launch {
            repository.getDesbravadorByName("%${input}%").collect {
                _desbravadoresByName.value = it
                it.forEach {
                    Log.i(TAG, "Desbravador: ${it.nome}")
                }
            }
        }
    }

    suspend fun getDesbravadorById(id: Long): Desbravador {
        val receivedDesbravador = viewModelScope.async(Dispatchers.IO) {
            return@async repository.getDesbravadorById(id)
        }
        return receivedDesbravador.await()
    }

    init {
        collectUnidades()
        collectDesbravadores()
    }

    private fun collectUnidades() {
        viewModelScope.launch {
            repository.getAllUnidades().collect{
                _unidades.value = it
            }
        }
    }

    private fun collectDesbravadores() {
        viewModelScope.launch {
            repository.getAllDesbravadores().collect{
                _desbravadores.value = it
            }
        }
    }
}


