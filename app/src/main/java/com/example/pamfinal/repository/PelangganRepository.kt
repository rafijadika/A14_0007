package com.example.pamfinal.repository

import com.example.pamfinal.model.Pelanggan
import com.example.pamfinal.serviceAPI.PelangganApiService

interface PelangganRepository {
    // Mendapatkan daftar semua pelanggan
    suspend fun getAllPelanggan(): List<Pelanggan>

    // Membuat pelanggan baru
    suspend fun createPelanggan(pelanggan: Pelanggan): Pelanggan

    // Mengupdate pelanggan berdasarkan ID
    suspend fun updatePelanggan(id: Int, pelanggan: Pelanggan): Pelanggan

    // Menghapus pelanggan berdasarkan ID
    suspend fun deletePelanggan(id: Int)

    // Mendapatkan detail pelanggan berdasarkan ID
    suspend fun getPelangganById(id: Int): Pelanggan
}

class NetworkPelangganRepository(private val pelangganApiService: PelangganApiService) : PelangganRepository {
    override suspend fun getAllPelanggan(): List<Pelanggan> {
        return pelangganApiService.getAllPelanggan()
    }

    override suspend fun createPelanggan(pelanggan: Pelanggan): Pelanggan {
        return pelangganApiService.createPelanggan(pelanggan)
    }

    override suspend fun updatePelanggan(id: Int, pelanggan: Pelanggan): Pelanggan {
        return pelangganApiService.updatePelanggan(id, pelanggan)
    }

    override suspend fun deletePelanggan(id: Int) {
        pelangganApiService.deletePelanggan(id)
    }

    override suspend fun getPelangganById(id: Int): Pelanggan {
        return pelangganApiService.getPelangganById(id)
    }
}