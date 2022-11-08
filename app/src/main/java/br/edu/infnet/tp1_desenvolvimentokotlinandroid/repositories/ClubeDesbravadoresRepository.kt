package br.edu.infnet.tp1_desenvolvimentokotlinandroid.repositories

import android.content.Context
import androidx.room.Room
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.database.ClubeDesbravadoresDatabase
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Desbravador
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Unidade
import kotlinx.coroutines.flow.Flow

private const val DATABASE_NAME = "clubedesbravadores_db"

class ClubeDesbravadoresRepository private constructor(context: Context) {

    private val database: ClubeDesbravadoresDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            ClubeDesbravadoresDatabase::class.java,
            DATABASE_NAME
        )
        .build()

    companion object {
        private var INSTANCE: ClubeDesbravadoresRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ClubeDesbravadoresRepository(context)
            }
        }

        fun get(): ClubeDesbravadoresRepository {
            return INSTANCE
                ?: throw IllegalStateException("ClubeDesbravadoresRepository deve ser inicializado.")
        }
    }

    //////////////////////////////////////////////////
    // DAO Unidade //////////////////////////////////
    ////////////////////////////////////////////////


    fun getAllUnidades(): Flow<List<Unidade>> = database.unidadeDao().getAll()

    fun insertUnidade(unidade: Unidade) {
        database.unidadeDao().insert(unidade)
    }

    fun getUnidadeById(input: Long): Unidade {
        return database.unidadeDao().getById(input)
    }

    fun updateUnidade(unidade: Unidade){
        database.unidadeDao().update(unidade)
    }

    fun deleteUnidade(unidade: Unidade){
        database.unidadeDao().delete(unidade)
    }

    //////////////////////////////////////////////////
    // DAO Desbravador //////////////////////////////
    ////////////////////////////////////////////////

    fun getAllDesbravadores(): Flow<List<Desbravador>> = database.desbravadorDao().getAll()

    fun insertDesbravador(desbravador: Desbravador) {
        database.desbravadorDao().insert(desbravador)
    }

    fun getDesbravadorById(desbravadorId: Long): Desbravador {
        return database.desbravadorDao().getById(desbravadorId)
    }

    fun updateDesbravador(desbravador: Desbravador){
        database.desbravadorDao().update(desbravador)
    }

    fun deleteDesbravador(desbravador: Desbravador){
        database.desbravadorDao().delete(desbravador)
    }

    fun getDesbravadorByName(input: String): Flow<List<Desbravador>> = database
        .desbravadorDao()
        .getListByName(input)
}