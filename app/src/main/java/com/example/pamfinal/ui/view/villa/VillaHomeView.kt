package com.example.pamfinal.ui.view.villa

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pamfinal.R
import com.example.pamfinal.model.Villa
import com.example.pamfinal.ui.viewmodel.PenyediaViewModel
import com.example.pamfinal.ui.viewmodel.villa.VillaHomeViewModel
import com.example.pamfinal.ui.viewmodel.villa.VillaUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VillaScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    navController: NavController, // Pass the navController here
    onBackClick: () -> Unit = {},
    viewModel: VillaHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Daftar Villa",  // Set title text
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black,  // Set blue color
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.getVillas() }) {
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
                modifier = Modifier.padding(16.dp) // Add padding to the FAB
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Villa")
            }
        },
    ) { innerPadding ->
        VillaStatus(
            villaUiState = viewModel.villaUiState,
            retryAction = { viewModel.getVillas() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteVilla(it.idVilla.toString())
                viewModel.getVillas()
            }
        )
    }
}

@Composable
fun VillaStatus(
    villaUiState: VillaUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Villa) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (villaUiState) {
        is VillaUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is VillaUiState.Success -> {
            if (villaUiState.villas.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data villa", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                VillaLayout(
                    villas = villaUiState.villas,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.idVilla.toString()) },
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        }
        is VillaUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.no_wifi),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.no_wifi), contentDescription = "")
            Text(
                text = stringResource(R.string.loading_failed),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = retryAction, modifier = Modifier.padding(top = 16.dp)) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Composable
fun VillaLayout(
    villas: List<Villa>,
    modifier: Modifier = Modifier,
    onDetailClick: (Villa) -> Unit,
    onDeleteClick: (Villa) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(villas) { villa ->
            VillaCard(
                villa = villa,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(villa) },
                onDeleteClick = { onDeleteClick(villa) }
            )
        }
    }
}

@Composable
fun VillaCard(
    villa: Villa,
    modifier: Modifier = Modifier,
    onDeleteClick: (Villa) -> Unit = {},
    onEditClick: (Villa) -> Unit = {}
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
                    text = "Villa ID: ${villa.idVilla}",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))

                // Delete Button
                IconButton(onClick = { onDeleteClick(villa) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Villa",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                // Edit Button
                IconButton(onClick = { onEditClick(villa) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Villa",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Divider()

            Column {
                Text(
                    text = "Nama Villa: ${villa.namaVilla}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Alamat: ${villa.alamatVilla}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Kamar Tersedia: ${villa.KamarTersedia}", // KamarTersedia sekarang Int
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}