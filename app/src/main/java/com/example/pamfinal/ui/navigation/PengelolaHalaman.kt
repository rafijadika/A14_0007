package com.example.pamfinal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pamfinal.ui.HomeApp
import com.example.pamfinal.ui.PelangganScreen
import com.example.pamfinal.ui.view.reservasi.ReservasiScreen
import com.example.pamfinal.ui.view.review.ReviewScreen
import com.example.pamfinal.ui.view.villa.VillaDetailScreen
import com.example.pamfinal.ui.view.villa.VillaScreen

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiMainScreen.route
    ) {
        // ========================== Main Screen Destination ==========================
        composable(route = DestinasiMainScreen.route) {
            HomeApp(
                onHalamanPelanggan = { navController.navigate(DestinasiHomePelanggan.route) },
                onHalamanReservasi = { navController.navigate(DestinasiHomeReservasi.route) },
                onHalamanReview = { navController.navigate(DestinasiHomeReview.route) },
                onHalamanVilla = { navController.navigate(DestinasiHomeVilla.route) }
            )
        }

        // ========================== Villa Destinations ==========================
        composable(route = DestinasiHomeVilla.route) {
            VillaScreen(
                navController = navController,
                navigateToItemEntry = { navController.navigate(DestinasiHomeVilla.route) },
                onDetailClick = { villaId ->
                    navController.navigate("${DestinasiDetailVilla}/${villaId}")
                }

            )
        }

        composable(
            DestinasiDetailVilla.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailVilla.VILLA_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val villaId = backStackEntry.arguments?.getString(DestinasiDetailVilla.VILLA_ID)
            villaId?.let {
                // Navigasi detail villa dengan villaId yang diteruskan
                VillaDetailScreen(
                    villaId = it,  // Pastikan untuk mengirimkan villaId yang diterima
                    navigateBack = {
                        navController.navigate(DestinasiHomeVilla.route) {
                            popUpTo(DestinasiHomeVilla.route) { inclusive = true }
                        }
                    },
                    navigateToEdit = {
                        // Navigasi ke edit jika diperlukan
                    }
                )
            }
        }



        // ========================== Detail Villa Destination ==========================

        // ========================== Pelanggan Destinations ==========================
        composable(route = DestinasiHomePelanggan.route) {
            PelangganScreen(
                navigateToItemEntry = { navController.navigate(DestinasiHomePelanggan.route) },
                navController = navController
            )
        }

        // ========================== Reservasi Destinations ==========================
        composable(route = DestinasiHomeReservasi.route) {
            ReservasiScreen(
                navigateToItemEntry = { navController.navigate(DestinasiHomeReservasi.route) },
                navController = navController
            )
        }

        // ========================== Review Destinations ==========================
        composable(route = DestinasiHomeReview.route) {
            ReviewScreen(
                navigateToItemEntry = { navController.navigate(DestinasiHomeVilla.route) },
                navController = navController
            )
        }
    }
}

