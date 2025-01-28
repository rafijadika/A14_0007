package com.example.pamfinal.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

// ========================== Villa Destinations ==========================
// MAIN SCREEN
object DestinasiMainScreen : DestinasiNavigasi {
    override val route = "mainScreen"
    override val titleRes = "Main Screen"
}

object DestinasiHomeVilla : DestinasiNavigasi {
    override val route = "homeVilla"
    override val titleRes = "Daftar Villa"
}

object DestinasiEntryVilla : DestinasiNavigasi {
    override val route = "entryVilla"
    override val titleRes = "Tambah Villa"
}

object DestinasiDetailVilla : DestinasiNavigasi {
    override val route = "detailVilla"
    override val titleRes = "Detail Villa"
    const val VILLA_ID = "villaId"
    val routeWithArg = "$route/{$VILLA_ID}"
}


object DestinasiUpdateVilla : DestinasiNavigasi {
    override val route = "updateVilla"
    override val titleRes = "Edit Villa"
    const val VILLA_ID = "villaId"
    val routeWithArg = "$route/{$VILLA_ID}"
}

// ========================== Pelanggan Destinations ==========================
object DestinasiHomePelanggan : DestinasiNavigasi {
    override val route = "homePelanggan"
    override val titleRes = "Daftar Pelanggan"
}

// ========================== Reservasi Destinations ==========================
object DestinasiHomeReservasi : DestinasiNavigasi {
    override val route = "homeReservasi"
    override val titleRes = "Daftar Reservasi"
}

// ========================== Review Destinations ==========================
object DestinasiHomeReview : DestinasiNavigasi {
    override val route = "homeReview"
    override val titleRes = "Daftar Review"
}