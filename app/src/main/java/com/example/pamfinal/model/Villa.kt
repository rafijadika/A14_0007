package com.example.pamfinal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Villa(
    @SerialName("id_villa")
    val idVilla: Int, // Ubah ke Int
    @SerialName("nama_villa")
    val namaVilla: String,
    @SerialName("alamat")
    val alamatVilla: String,
    @SerialName("Kamar_tersedia")
    val KamarTersedia: Int // Ubah ke Int
)