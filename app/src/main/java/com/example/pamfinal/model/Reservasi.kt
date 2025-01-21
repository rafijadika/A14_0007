package com.example.pamfinal.model

data class Reservasi(
    val id_reservasi: Int,      // ID unik untuk reservasi
    val id_villa: Int,          // ID villa yang direservasi
    val id_pelanggan: Int,      // ID pelanggan yang melakukan reservasi
    val Check_in: String,       // Tanggal check-in (format: "yyyy-MM-dd")
    val Check_out: String,      // Tanggal check-out (format: "yyyy-MM-dd")
    val Jumlah_kamar: Int       // Jumlah kamar yang dipesan
)