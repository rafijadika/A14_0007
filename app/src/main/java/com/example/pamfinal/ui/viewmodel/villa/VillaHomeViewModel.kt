package com.example.pamfinal.ui.viewmodel.villa

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamfinal.model.Villa
import com.example.pamfinal.repository.VillaRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class VillaUiState {
    data class Success(val villas: List<Villa>) : VillaUiState()
    object Error : VillaUiState()
    object Loading : VillaUiState()
}

class VillaHomeViewModel(
    private val repository: VillaRepository
) : ViewModel() {

    var villaUiState: VillaUiState by mutableStateOf(VillaUiState.Loading)
        private set

    init {
        getVillas()
    }

    fun getVillas() {
        viewModelScope.launch {
            villaUiState = VillaUiState.Loading
            try {
                val villaList = repository.getAllVillas()
                villaUiState = VillaUiState.Success(villaList)
            } catch (e: IOException) {
                Log.e("VillaHomeViewModel", "Network error", e)
                villaUiState = VillaUiState.Error
            } catch (e: Exception) {
                Log.e("VillaHomeViewModel", "Unexpected error", e)
                villaUiState = VillaUiState.Error
            }
        }
    }

    fun deleteVilla(id: String) {
        viewModelScope.launch {
            try {
                repository.deleteVilla(id)
                getVillas() // Refresh the list after deletion
            } catch (e: IOException) {
                Log.e("VillaHomeViewModel", "Network error", e)
                villaUiState = VillaUiState.Error
            } catch (e: Exception) {
                Log.e("VillaHomeViewModel", "Unexpected error", e)
                villaUiState = VillaUiState.Error
            }
        }
    }
}