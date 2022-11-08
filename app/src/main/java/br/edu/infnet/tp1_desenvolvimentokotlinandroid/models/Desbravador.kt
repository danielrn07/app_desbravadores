package br.edu.infnet.tp1_desenvolvimentokotlinandroid.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Unidade::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("unidadeId"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Desbravador(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String,
    val idade: Int,
    val email: String,
    val unidadeId: Long = 0L
)
