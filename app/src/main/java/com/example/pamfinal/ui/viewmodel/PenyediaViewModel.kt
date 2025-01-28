package com.example.pamfinal.ui.viewmodel

import com.example.pamfinal.app.VillaApplication
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pamfinal.ui.viewmodel.pelanggan.PelangganHomeViewModel
import com.example.pamfinal.ui.viewmodel.reservasi.ReservasiHomeViewModel
import com.example.pamfinal.ui.viewmodel.villa.VillaHomeViewModel
import com.example.pamfinal.ui.viewmodel.review.ReviewHomeViewModel
import com.example.pamfinal.ui.viewmodel.villa.VillaDetailViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // ViewModel untuk Villa
        initializer {
            VillaHomeViewModel(
                VillaApplication().container.villaRepository
            )
        }



        initializer {
            VillaDetailViewModel(
                createSavedStateHandle(),
                VillaApplication().container.villaRepository)
        }

        // ViewModel untuk Pelanggan
        initializer {
            PelangganHomeViewModel(
                VillaApplication().container.pelangganRepository
            )
        }

        // ViewModel untuk Reservasi
        initializer {
            ReservasiHomeViewModel(
                VillaApplication().container.reservasiRepository
            )
        }

        // ViewModel untuk Review
        initializer {
            ReviewHomeViewModel(
                VillaApplication().container.reviewRepository
            )
        }
    }
}

// Extension function untuk mendapatkan instance aplikasi
fun CreationExtras.VillaApplication(): VillaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VillaApplication)