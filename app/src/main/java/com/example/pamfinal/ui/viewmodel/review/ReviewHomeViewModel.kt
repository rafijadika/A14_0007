package com.example.pamfinal.ui.viewmodel.review


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamfinal.model.Review
import com.example.pamfinal.repository.ReviewRepository
import kotlinx.coroutines.launch
import java.io.IOException

// Define UI states
sealed class ReviewUiState {
    data class Success(val reviews: List<Review>) : ReviewUiState()
    object Error : ReviewUiState()
    object Loading : ReviewUiState()
}

class ReviewHomeViewModel(
    private val repository: ReviewRepository // Injecting ReviewRepository
) : ViewModel() {

    // UI State to manage the list of Review
    var reviewUiState: ReviewUiState by mutableStateOf(ReviewUiState.Loading)
        private set

    init {
        getReviews() // Get Review data when ViewModel is initialized
    }

    // Function to fetch Review data
    fun getReviews() {
        Log.d("ReviewHomeViewModel", "Starting getReviews")
        viewModelScope.launch {
            reviewUiState = ReviewUiState.Loading // Start by showing loading state
            try {
                val reviewList = repository.getAllReview() // Fetch data from the repository
                Log.d("ReviewHomeViewModel", "Fetched reviews: $reviewList")
                reviewUiState = ReviewUiState.Success(reviewList) // Update state on success
            } catch (e: IOException) {
                Log.e("ReviewHomeViewModel", "Network error while fetching reviews: ${e.message}")
                reviewUiState = ReviewUiState.Error // Handle network issues (e.g., no internet)
            } catch (e: Exception) {
                Log.e("ReviewHomeViewModel", "Unexpected error while fetching reviews: ${e.message}")
                reviewUiState = ReviewUiState.Error // Handle unexpected errors (generic)
            }
        }
    }

    // Function to delete a Review by ID
    fun deleteReview(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteReview(id.toString()) // Call repository to delete the review
                getReviews() // Refresh list after deletion
            } catch (e: IOException) {
                reviewUiState = ReviewUiState.Error // Handle network issues during deletion
            } catch (e: Exception) {
                reviewUiState = ReviewUiState.Error // Handle any other unexpected errors during deletion
            }
        }
    }
}