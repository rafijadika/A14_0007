package com.example.pamfinal.ui.viewmodel.reservasi

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamfinal.model.Reservasi
import com.example.pamfinal.repository.ReservasiRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class ReservasiUiState {
    data class Success(val reservasiList: List<Reservasi>) : ReservasiUiState()
    object Error : ReservasiUiState()
    object Loading : ReservasiUiState()
}

class ReservasiHomeViewModel(
    private val repository: ReservasiRepository
) : ViewModel() {

    var reservasiUiState: ReservasiUiState by mutableStateOf(ReservasiUiState.Loading)
        private set

    init {
        getReservasi()
    }

    fun getReservasi() {
        viewModelScope.launch {
            reservasiUiState = ReservasiUiState.Loading
            try {
                val reservasiList = repository.getAllReservasi()
                reservasiUiState = ReservasiUiState.Success(reservasiList)
            } catch (e: IOException) {
                Log.e("ReservasiHomeViewModel", "Network error", e)
                reservasiUiState = ReservasiUiState.Error
            } catch (e: Exception) {
                Log.e("ReservasiHomeViewModel", "Unexpected error", e)
                reservasiUiState = ReservasiUiState.Error
            }
        }
    }

    fun deleteReservasi(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteReservasi(id)
                getReservasi() // Refresh the list after deletion
            } catch (e: IOException) {
                Log.e("ReservasiHomeViewModel", "Network error", e)
                reservasiUiState = ReservasiUiState.Error
            } catch (e: Exception) {
                Log.e("ReservasiHomeViewModel", "Unexpected error", e)
                reservasiUiState = ReservasiUiState.Error
            }
        }
    }
}
