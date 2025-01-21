package com.example.pamfinal.model

data class Review(
    val id_review: Int,         // ID unik untuk review
    val id_reservasi: Int,      // ID reservasi yang direview
    val nilai: String,          // Nilai kepuasan (Sangat Puas, Puas, Biasa, Tidak Puas, Sangat Tidak Puas)
    val komentar: String        // Komentar dari pelanggan
)