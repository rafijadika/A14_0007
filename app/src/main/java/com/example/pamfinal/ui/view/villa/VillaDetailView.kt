package com.example.pamfinal.ui.view.villa




import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pamfinal.model.Villa
import com.example.pamfinal.ui.navigation.DestinasiNavigasi
import com.example.pamfinal.ui.viewmodel.PenyediaViewModel
import com.example.pamfinal.ui.viewmodel.villa.DetailVillaUiState
import com.example.pamfinal.ui.viewmodel.villa.VillaDetailViewModel
import com.example.pamfinal.ui.viewmodel.villa.VillaUiEvent


object DestinasiDetailVilla : DestinasiNavigasi {
    override val route = "detail villa"
    const val ID_VILLA = "id_villa"
    override val titleRes = "Detail Villa"
    val routeWithArg = "$route/{$ID_VILLA}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VillaDetailScreen(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VillaDetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
    villaId: String
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Detail Villa",  // Set title text
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black,  // Set blue color
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.getVillaById() }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEdit,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Villa"
                )
            }
        }
    ) { innerPadding ->
        BodyDetailVilla(
            detailVillaUiState = viewModel.detailVillaUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailVilla(
    detailVillaUiState: DetailVillaUiState,
    modifier: Modifier = Modifier
) {
    when {
        detailVillaUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailVillaUiState.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailVillaUiState.errorMessage,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        detailVillaUiState.isUiVillaNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailVilla(
                    villa = detailVillaUiState.detailVillaUiEvent.toVilla(),
                    modifier = modifier
                )
            }
        }
    }
}

fun VillaUiEvent.toVilla(): Villa = Villa(
    idVilla = idVilla.toInt(), // Konversi String ke Int
    namaVilla = namaVilla,
    alamatVilla = alamatVilla,
    KamarTersedia = kamarTersedia.toInt() // Konversi String ke Int
)

@Composable
fun ItemDetailVilla(
    modifier: Modifier = Modifier,
    villa: Villa,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E8A68), // Dark Blue background for card
            contentColor = Color.White // White text color for contrast
        ),
        shape = MaterialTheme.shapes.medium, // Rounded corners
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ComponentDetailVilla(judul = "ID Villa", isinya = villa.idVilla.toString())
            Spacer(modifier = Modifier.padding(8.dp))
            ComponentDetailVilla(judul = "Nama Villa", isinya = villa.namaVilla)
            Spacer(modifier = Modifier.padding(8.dp))
            ComponentDetailVilla(judul = "Alamat Villa", isinya = villa.alamatVilla)
            Spacer(modifier = Modifier.padding(8.dp))
            ComponentDetailVilla(judul = "Kamar Tersedia", isinya = villa.KamarTersedia.toString())
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun ComponentDetailVilla(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        // Title with blue color
        Text(
            text = "$judul : ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White // Dark blue for titles
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Content with black color for better readability
        Text(
            text = isinya,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black // Black color for content text
        )
    }
}
