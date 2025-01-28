package com.example.pamfinal.serviceAPI

import com.example.pamfinal.model.Reservasi
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReservasiApiService {
    // Mendapatkan daftar semua reservasi
    @GET("api/reservasi")
    suspend fun getAllReservasi(): List<Reservasi>

    // Membuat reservasi baru
    @POST("api/reservasi")
    suspend fun createReservasi(@Body reservasi: Reservasi): Reservasi

    // Mengupdate reservasi berdasarkan ID
    @PUT("api/reservasi/{id}")
    suspend fun updateReservasi(@Path("id") id: Reservasi, @Body reservasi: Reservasi): Reservasi

    // Menghapus reservasi berdasarkan ID
    @DELETE("api/reservasi/{id}")
    suspend fun deleteReservasi(@Path("id") id: Int)

    // Mendapatkan detail reservasi berdasarkan ID
    @GET("api/reservasi/{id}")
    suspend fun getReservasiById(@Path("id") id: Int): Reservasi
}