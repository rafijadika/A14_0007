package com.example.pamfinal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pelanggan(
    @SerialName("id_pelanggan")
    val idPelanggan: Int,
    @SerialName("nama_pelanggan") // ID unik untuk pelanggan
    val namaPelanggan: String,
    @SerialName("No_hp")    // Nama pelanggan
    val noHp: String           // Nomor handphone pelanggan
)