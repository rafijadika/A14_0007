package com.example.pamfinal.serviceAPI

import com.example.pamfinal.model.Pelanggan
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PelangganApiService {
    // Mendapatkan daftar semua pelanggan
    @GET("api/pelanggan")
    suspend fun getAllPelanggan(): List<Pelanggan>

    // Membuat pelanggan baru
    @POST("pelanggan")
    suspend fun createPelanggan(@Body pelanggan: Pelanggan): Pelanggan

    // Mengupdate pelanggan berdasarkan ID
    @PUT("pelanggan/{id}")
    suspend fun updatePelanggan(@Path("id") id: Int, @Body pelanggan: Pelanggan): Pelanggan

    // Menghapus pelanggan berdasarkan ID
    @DELETE("pelanggan/{id}")
    suspend fun deletePelanggan(@Path("id") id: Int)

    // Mendapatkan detail pelanggan berdasarkan ID
    @GET("pelanggan/{id}")
    suspend fun getPelangganById(@Path("id") id: Int): Pelanggan
}