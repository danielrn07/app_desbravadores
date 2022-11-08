package br.edu.infnet.tp1_desenvolvimentokotlinandroid.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Unidade(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String
)
