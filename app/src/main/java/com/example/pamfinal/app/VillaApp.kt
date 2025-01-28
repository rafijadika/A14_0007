package com.example.pamfinal.app


import android.app.Application
import com.example.pamfinal.di.AppContainer
import com.example.pamfinal.di.TugasContainer


class VillaApplication : Application() {
    // Properti untuk menyimpan instance AppContainer
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        // Inisialisasi TugasContainer saat aplikasi dimulai
        container = TugasContainer()
    }
}