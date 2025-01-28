package com.example.pamfinal.ui.viewmodel.pelanggan


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamfinal.model.Pelanggan
import com.example.pamfinal.repository.PelangganRepository
import kotlinx.coroutines.launch
import java.io.IOException

// Define UI states
sealed class PelangganUiState {
    data class Success(val pelangganList: List<Pelanggan>) : PelangganUiState()
    object Error : PelangganUiState()
    object Loading : PelangganUiState()
}

class PelangganHomeViewModel(
    private val repository: PelangganRepository // Injecting PelangganRepository
) : ViewModel() {

    // UI State to manage the list of Pelanggan
    var pelangganUiState: PelangganUiState by mutableStateOf(PelangganUiState.Loading)
        private set

    init {
        Log.d("PelangganHomeViewModel", "ViewModel initialized") // Debug log for initialization
        getPelanggans() // Get Pelanggan data when ViewModel is initialized
    }

    // Function to fetch Pelanggan data
    fun getPelanggans() {
        viewModelScope.launch {
            Log.d("PelangganHomeViewModel", "Start fetching pelanggan data...") // Log untuk memulai proses pengambilan data
            pelangganUiState = PelangganUiState.Loading // Start by showing loading state
            try {
                Log.d("PelangganHomeViewModel", "Fetching pelanggan data from repository...") // Log saat mulai mengambil data dari repository
                val pelangganList = repository.getAllPelanggan() // Fetch data from the repository
                Log.d("PelangganHomeViewModel", "Fetched pelanggans: ${pelangganList.size} items") // Log jumlah data yang berhasil diambil
                pelangganUiState = PelangganUiState.Success(pelangganList) // Update state on success
                Log.d("PelangganHomeViewModel", "Pelanggan data successfully loaded.") // Log jika pengambilan data berhasil
            } catch (e: IOException) {
                Log.e("PelangganHomeViewModel", "Network error while fetching pelanggans", e) // Log jika terjadi kesalahan jaringan
                pelangganUiState = PelangganUiState.Error // Handle network issues (e.g., no internet)
                Log.d("PelangganHomeViewModel", "Failed to fetch data due to network error.") // Log jika gagal karena masalah jaringan
            } catch (e: Exception) {
                Log.e("PelangganHomeViewModel", "Unexpected error while fetching pelanggans", e) // Log untuk error lainnya
                pelangganUiState = PelangganUiState.Error // Handle unexpected errors (generic)
                Log.d("PelangganHomeViewModel", "Failed to fetch data due to an unexpected error.") // Log untuk error tak terduga
            }
        }
    }


    // Function to delete a Pelanggan by ID
    fun deletePelanggan(id: Int) {
        viewModelScope.launch {
            try {
                Log.d("PelangganHomeViewModel", "Deleting pelanggan with ID: $id")
                repository.deletePelanggan(id) // Call repository to delete the pelanggan
                Log.d("PelangganHomeViewModel", "Pelanggan deleted, refreshing list...")
                getPelanggans() // Refresh list after deletion
            } catch (e: IOException) {
                Log.e("PelangganHomeViewModel", "Network error while deleting pelanggan", e)
                pelangganUiState = PelangganUiState.Error // Handle network issues during deletion
            } catch (e: Exception) {
                Log.e("PelangganHomeViewModel", "Unexpected error while deleting pelanggan", e)
                pelangganUiState = PelangganUiState.Error // Handle unexpected errors during deletion
            }
        }
    }
}
