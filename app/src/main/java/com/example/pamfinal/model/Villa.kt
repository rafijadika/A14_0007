package com.example.pamfinal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Villa(
    @SerialName("id_villa")
    val idVilla: Int,
    @SerialName("nama_villa")// ID unik untuk villa
    val namaVilla: String,     // Nama villa
    @SerialName("alamat")
    val alamatVilla: String,         // Alamat villa
    @SerialName("Kamar_tersedia")
    val KamarTersedia: Int     // Jumlah kamar yang tersedia
)