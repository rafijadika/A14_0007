package com.example.pamfinal.repository

import com.example.pamfinal.model.Reservasi
import com.example.pamfinal.serviceAPI.ReservasiApiService

interface ReservasiRepository {
    // Mendapatkan daftar semua reservasi
    suspend fun getAllReservasi(): List<Reservasi>

    // Membuat reservasi baru
    suspend fun createReservasi(reservasi: Reservasi): Reservasi

    // Mengupdate reservasi berdasarkan ID reservasi
    suspend fun updateReservasi(id: Reservasi): Reservasi

    // Menghapus reservasi berdasarkan ID reservasi
    suspend fun deleteReservasi(id: Int)

    // Mendapatkan detail reservasi berdasarkan ID reservasi
    suspend fun getReservasiById(id: Int): Reservasi
}

class NetworkReservasiRepository(private val reservasiApiService: ReservasiApiService) : ReservasiRepository {
    override suspend fun getAllReservasi(): List<Reservasi> {
        return reservasiApiService.getAllReservasi()
    }

    override suspend fun createReservasi(reservasi: Reservasi): Reservasi {
        return reservasiApiService.createReservasi(reservasi)
    }

    override suspend fun updateReservasi(id: Reservasi): Reservasi {
        return reservasiApiService.updateReservasi(id, reservasi = id)
    }

    override suspend fun deleteReservasi(id: Int) {
        reservasiApiService.deleteReservasi(id)
    }

    override suspend fun getReservasiById(id: Int): Reservasi {
        return reservasiApiService.getReservasiById(id)
    }
}
