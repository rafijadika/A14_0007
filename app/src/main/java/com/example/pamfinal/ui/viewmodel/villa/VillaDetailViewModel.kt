package com.example.pamfinal.ui.viewmodel.villa

import com.example.pamfinal.repository.VillaRepository
import com.example.pamfinal.ui.navigation.DestinasiDetailVilla.VILLA_ID
import com.example.pamfinal.ui.navigation.DestinasiNavigasi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamfinal.model.Villa
import kotlinx.coroutines.launch

object DestinasiDetailVilla : DestinasiNavigasi {
    override val route = "detail villa"
    const val villaid = "id_villa"
    override val titleRes = "Detail Villa"
    val routeWithArg = "$route/{$VILLA_ID}"
}

class VillaDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val villaRepository: VillaRepository
) : ViewModel() {
    private val villaid: String = checkNotNull(savedStateHandle[VILLA_ID])

    var detailVillaUiState: DetailVillaUiState by mutableStateOf(DetailVillaUiState())
        private set

    init {
        getVillaById()
    }

    // Fetch villa detail by ID
    fun getVillaById() {
        viewModelScope.launch {
            detailVillaUiState = detailVillaUiState.copy(isLoading = true) // Indicate loading state
            try {
                val result = villaRepository.getVillaById(villaid.toInt())
                detailVillaUiState = DetailVillaUiState(
                    detailVillaUiEvent = result.toDetailVillaUiEvent(),
                    isLoading = false,
                    isError = false,
                    errorMessage = ""
                )
            } catch (e: Exception) {
                detailVillaUiState = DetailVillaUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

// UI state for Villa detail
data class DetailVillaUiState(
    val detailVillaUiEvent: VillaUiEvent = VillaUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    // Check if the UI data is empty
    val isUiVillaEmpty: Boolean
        get() = detailVillaUiEvent == VillaUiEvent()

    // Check if the UI data is not empty
    val isUiVillaNotEmpty: Boolean
        get() = detailVillaUiEvent != VillaUiEvent()
}

// Event data class for Villa
data class VillaUiEvent(
    val idVilla: String = "",
    val namaVilla: String = "",
    val alamatVilla: String = "",
    val kamarTersedia: String = ""
)

// Extension function to map Villa model to VillaUiEvent
fun Villa.toDetailVillaUiEvent(): VillaUiEvent {
    return VillaUiEvent(
        idVilla = idVilla.toString(),
        namaVilla = namaVilla,
        alamatVilla = alamatVilla,
        kamarTersedia = KamarTersedia.toString()
    )
}
