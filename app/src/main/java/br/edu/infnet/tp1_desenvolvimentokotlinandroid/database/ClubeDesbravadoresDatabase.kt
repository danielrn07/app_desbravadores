package br.edu.infnet.tp1_desenvolvimentokotlinandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.daos.DesbravadorDao
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.daos.UnidadeDao
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Desbravador
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Unidade

@Database(
    entities = arrayOf(Unidade::class, Desbravador::class),
    version = 1,
    exportSchema = false
)

abstract class ClubeDesbravadoresDatabase: RoomDatabase() {
    abstract fun unidadeDao(): UnidadeDao
    abstract fun desbravadorDao(): DesbravadorDao
}