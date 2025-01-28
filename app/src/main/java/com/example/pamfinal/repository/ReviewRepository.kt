package com.example.pamfinal.repository

import com.example.pamfinal.model.Review
import com.example.pamfinal.serviceAPI.ReviewApiService

interface ReviewRepository {
    // Mendapatkan daftar semua review
    suspend fun getAllReview(): List<Review>

    // Membuat review baru
    suspend fun createReview(review: Review): Review

    // Mengupdate review berdasarkan ID
    suspend fun updateReview(id: Int, review: Review): Review

    // Menghapus review berdasarkan ID
    suspend fun deleteReview(id: String)

    // Mendapatkan detail review berdasarkan ID
    suspend fun getReviewById(id: Int): Review
}

class NetworkReviewRepository(private val reviewApiService: ReviewApiService) : ReviewRepository {
    override suspend fun getAllReview(): List<Review> {
        return reviewApiService.getAllReview()
    }

    override suspend fun createReview(review: Review): Review {
        return reviewApiService.createReview(review)
    }

    override suspend fun updateReview(id: Int, review: Review): Review {
        return reviewApiService.updateReview(id, review)
    }

    override suspend fun deleteReview(id: String) {
        reviewApiService.deleteReview(id)
    }

    override suspend fun getReviewById(id: Int): Review {
        return reviewApiService.getReviewById(id)
    }
}