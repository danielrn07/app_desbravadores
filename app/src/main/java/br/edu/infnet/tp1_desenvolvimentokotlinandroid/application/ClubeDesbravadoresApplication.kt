package br.edu.infnet.tp1_desenvolvimentokotlinandroid.application

import android.app.Application
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.repositories.ClubeDesbravadoresRepository
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.repositories.ClubeDesbravadoresRepository.Companion.initialize

class ClubeDesbravadoresApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ClubeDesbravadoresRepository.initialize(this)
    }
}