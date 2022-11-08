package br.edu.infnet.tp1_desenvolvimentokotlinandroid.daos

import androidx.room.*
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Desbravador
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Unidade
import kotlinx.coroutines.flow.Flow

@Dao
interface DesbravadorDao {
    // CRUD ->
    // CREATE:
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(desbravador: Desbravador)

    // READ:
    @Query("SELECT * FROM Desbravador WHERE id = :input")
    fun getById(input: Long): Desbravador

    // UPDATE:
    @Update
    fun update(desbravador: Desbravador)

    // DELETE
    @Delete
    fun delete(desbravador: Desbravador)

    // Selecionar todos e retornar em uma lista
    @Query("SELECT * FROM Desbravador")
    fun getAll(): Flow<List<Desbravador>>

    // Query
    @Query("SELECT * FROM Desbravador WHERE unidadeId = :input")
    fun getByCategoria(input: Long): Flow<List<Desbravador>>

    // Retorna uma lista de Alunos cujos nomes contenham o texto de input
    @Query("SELECT * FROM Desbravador WHERE nome like :input")
    fun getListByName(input: String): Flow<List<Desbravador>>
}