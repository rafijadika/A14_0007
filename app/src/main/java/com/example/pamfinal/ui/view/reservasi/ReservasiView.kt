package com.example.pamfinal.ui.view.reservasi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pamfinal.model.Reservasi
import com.example.pamfinal.ui.OnError
import com.example.pamfinal.ui.OnLoading
import com.example.pamfinal.ui.viewmodel.PenyediaViewModel
import com.example.pamfinal.ui.viewmodel.reservasi.ReservasiHomeViewModel
import com.example.pamfinal.ui.viewmodel.reservasi.ReservasiUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservasiScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    navController: NavController,
    onBackClick: () -> Unit = {},
    viewModel: ReservasiHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Daftar Reservasi",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.getReservasi() }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Reservasi")
            }
        },
    ) { innerPadding ->
        ReservasiStatus(
            reservasiUiState = viewModel.reservasiUiState,
            retryAction = { viewModel.getReservasi() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteReservasi(it.idReservasi.toInt())
                viewModel.getReservasi()
            }
        )
    }
}

@Composable
fun ReservasiStatus(
    reservasiUiState: ReservasiUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Reservasi) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (reservasiUiState) {
        is ReservasiUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is ReservasiUiState.Success -> {
            if (reservasiUiState.reservasiList.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data reservasi", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                ReservasiLayout(
                    reservasis = reservasiUiState.reservasiList,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.idReservasi.toString()) },
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        }
        is ReservasiUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ReservasiLayout(
    reservasis: List<Reservasi>,
    modifier: Modifier = Modifier,
    onDetailClick: (Reservasi) -> Unit,
    onDeleteClick: (Reservasi) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(reservasis) { reservasi ->
            ReservasiCard(
                reservasi = reservasi,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(reservasi) },
                onDeleteClick = { onDeleteClick(reservasi) }
            )
        }
    }
}

@Composable
fun ReservasiCard(
    reservasi: Reservasi,
    modifier: Modifier = Modifier,
    onDeleteClick: (Reservasi) -> Unit = {},
    onEditClick: (Reservasi) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
            pressedElevation = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFADD8E6)
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reservasi ID: ${reservasi.idReservasi}",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))

                // Delete Button
                IconButton(onClick = { onDeleteClick(reservasi) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Reservasi",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                // Edit Button
                IconButton(onClick = { onEditClick(reservasi) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Reservasi",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Divider()

            Column {
                Text(
                    text = "Check-In: ${reservasi.CheckIn}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Check-Out: ${reservasi.CheckOut}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Jumlah Kamar: ${reservasi.JumlahKamar}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
