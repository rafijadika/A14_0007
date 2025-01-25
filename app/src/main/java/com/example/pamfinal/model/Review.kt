package com.example.pamfinal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    @SerialName("id_review")
    val idReview: Int,
    @SerialName("id_reservasi")// ID unik untuk review
    val idReservasi: Int,
    @SerialName ("nilai")// ID nilai yang direview
    val nilaiReview: String,
    @SerialName("komentar")// Nilai komentar (Sangat Puas, Puas, Biasa, Tidak Puas, Sangat Tidak Puas)
    val komentarReview: String        // Komentar dari pelanggan
)