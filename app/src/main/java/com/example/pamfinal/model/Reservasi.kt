package com.example.pamfinal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reservasi(
    @SerialName("id_reservasi")
    val idReservasi: Int,      // ID unik untuk reservasi
    @SerialName("id_villa")
    val idVilla: Int,          // ID villa yang direservasi
    @SerialName("id_pelanggan")
    val idPelanggan: Int,      // ID pelanggan yang melakukan reservasi
    val CheckIn: String,       // Tanggal check-in (format: "yyyy-MM-dd")
    val CheckOut: String,      // Tanggal check-out (format: "yyyy-MM-dd")
    @SerialName("jumlah_kamar")
    val JumlahKamar: Int       // Jumlah kamar yang dipesan
)