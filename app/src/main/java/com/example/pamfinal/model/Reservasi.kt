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
    val idPelanggan: Int,
    @SerialName("Check_in")// ID pelanggan yang melakukan reservasi
    val CheckIn: String,
    @SerialName("Check_out")// Tanggal check-in (format: "yyyy-MM-dd")
    val CheckOut: String,      // Tanggal check-out (format: "yyyy-MM-dd")
    @SerialName("Jumlah_kamar")
    val JumlahKamar: Int       // Jumlah kamar yang dipesan
)