package com.example.pamfinal.di

import com.example.pamfinal.repository.NetworkPelangganRepository
import com.example.pamfinal.repository.NetworkReservasiRepository
import com.example.pamfinal.repository.NetworkReviewRepository
import com.example.pamfinal.repository.NetworkVillaRepository
import com.example.pamfinal.repository.PelangganRepository
import com.example.pamfinal.repository.ReservasiRepository
import com.example.pamfinal.repository.ReviewRepository
import com.example.pamfinal.repository.VillaRepository
import com.example.pamfinal.serviceAPI.PelangganApiService
import com.example.pamfinal.serviceAPI.ReservasiApiService
import com.example.pamfinal.serviceAPI.ReviewApiService
import com.example.pamfinal.serviceAPI.VillaApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val villaRepository: VillaRepository
    val pelangganRepository: PelangganRepository
    val reservasiRepository: ReservasiRepository
    val reviewRepository: ReviewRepository
}


class TugasContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2:3000/" // Ganti dengan URL API Anda
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    // Service API untuk Villa
    private val villaService: VillaApiService by lazy {
        retrofit.create(VillaApiService::class.java)
    }

    // Service API untuk Pelanggan
    private val pelangganService: PelangganApiService by lazy {
        retrofit.create(PelangganApiService::class.java)
    }

    // Service API untuk Reservasi
    private val reservasiService: ReservasiApiService by lazy {
        retrofit.create(ReservasiApiService::class.java)
    }

    // Service API untuk Review
    private val reviewService: ReviewApiService by lazy {
        retrofit.create(ReviewApiService::class.java)
    }



    // Repository untuk Villa
    override val villaRepository: VillaRepository by lazy {
        NetworkVillaRepository(villaService)
    }

    // Repository untuk Pelanggan
    override val pelangganRepository: PelangganRepository by lazy {
        NetworkPelangganRepository(pelangganService)
    }

    // Repository untuk Reservasi
    override val reservasiRepository: ReservasiRepository by lazy {
        NetworkReservasiRepository(reservasiService)
    }

    // Repository untuk Review
    override val reviewRepository: ReviewRepository by lazy {
        NetworkReviewRepository(reviewService)
    }
}