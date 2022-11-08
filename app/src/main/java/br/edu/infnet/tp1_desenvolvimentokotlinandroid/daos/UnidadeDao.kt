package br.edu.infnet.tp1_desenvolvimentokotlinandroid.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Unidade
import kotlinx.coroutines.flow.Flow

@Dao
interface UnidadeDao {

    // CRUD

    // CREATE:
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(unidade: Unidade)

    // READ:
    @Query("SELECT * FROM Unidade WHERE id = :input")
    fun getById(input: Long): Unidade

    // UPDATE:
    @Update
    fun update(unidade: Unidade)

    // DELETE
    @Delete
    fun delete(unidade: Unidade)

    // Selecionar todos e retornar em uma lista
//    @Query("SELECT * FROM Unidade")
//    fun getAll(): List<Unidade>

    // Retorna todos os Alunos
    @Query("SELECT * FROM Unidade")
    fun getAll() : Flow<List<Unidade>>
}