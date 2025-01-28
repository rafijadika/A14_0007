package com.example.pamfinal.serviceAPI

import com.example.pamfinal.model.Review
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReviewApiService {
    // Mendapatkan daftar semua review
    @GET("api/review/")
    suspend fun getAllReview(): List<Review>

    // Membuat review baru
    @POST("review")
    suspend fun createReview(@Body review: Review): Review

    // Mengupdate review berdasarkan ID
    @PUT("review/{id}")
    suspend fun updateReview(@Path("id") id: Int, @Body review: Review): Review

    // Menghapus review berdasarkan ID (menggunakan String untuk konsistensi dengan ViewModel)
    @DELETE("review/{id}")
    suspend fun deleteReview(@Path("id") id: String)

    // Mendapatkan detail review berdasarkan ID
    @GET("review/{id}")
    suspend fun getReviewById(@Path("id") id: Int): Review
}
