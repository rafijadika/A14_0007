package com.example.pamfinal.repository

import com.example.pamfinal.serviceAPI.VillaApiService

import com.example.pamfinal.model.Villa

interface VillaRepository {
    // Mendapatkan daftar semua villa
    suspend fun getAllVillas(): List<Villa>

    // Membuat villa baru
    suspend fun createVilla(villa: Villa): Villa

    // Mengupdate villa berdasarkan ID
    suspend fun updateVilla(id: Int, villa: Villa): Villa

    // Menghapus villa berdasarkan ID
    suspend fun deleteVilla(id: String)

    // Mendapatkan detail villa berdasarkan ID
    suspend fun getVillaById(id: Int): Villa
}

class NetworkVillaRepository(private val villaApiService: VillaApiService) : VillaRepository {
    override suspend fun getAllVillas(): List<Villa> = villaApiService.getAllVillas()

    override suspend fun createVilla(villa: Villa): Villa {
        return villaApiService.createVilla(villa)
    }

    override suspend fun updateVilla(id: Int, villa: Villa): Villa {
        return villaApiService.updateVilla(id, villa)
    }

    override suspend fun deleteVilla(id: String) {
        villaApiService.deleteVilla(id)
    }

    override suspend fun getVillaById(id: Int): Villa {
        return villaApiService.getVillaById(id)
    }
}