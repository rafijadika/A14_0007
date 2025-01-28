package com.example.pamfinal.serviceAPI

import com.example.pamfinal.model.Villa
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface VillaApiService {
    // Mendapatkan daftar semua villa
    @GET("api/villa")
    suspend fun getAllVillas(): List<Villa>

    // Membuat villa baru
    @POST("api/villa")
    suspend fun createVilla(@Body villa: Villa): Villa

    // Mengupdate villa berdasarkan ID
    @PUT("api/villa/{id}")
    suspend fun updateVilla(@Path("id") id: Int, @Body villa: Villa): Villa

    // Menghapus villa berdasarkan ID
    @DELETE("api/villa/{id}")
    suspend fun deleteVilla(@Path("id") id: String)

    // Mendapatkan detail villa berdasarkan ID
    @GET("api/villa/{id}")
    suspend fun getVillaById(@Path("id") id: Int): Villa
}