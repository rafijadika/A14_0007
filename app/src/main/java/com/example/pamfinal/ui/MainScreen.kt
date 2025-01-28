package com.example.pamfinal.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pamfinal.R

@Composable
fun HomeApp(
    modifier: Modifier = Modifier,
    onHalamanPelanggan: () -> Unit,
    onHalamanReservasi: () -> Unit,
    onHalamanReview: () -> Unit,
    onHalamanVilla: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White) // Warna latar belakang fallback
    ) {
        // Gambar latar belakang
        Image(
            painter = painterResource(id = R.drawable.villa_background), // Ganti dengan gambar latar belakang Anda
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.8f), // Sesuaikan opacity gambar latar belakang
            contentScale = ContentScale.Crop // Sesuaikan skala gambar
        )

        // Konten utama
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 35.dp),
            verticalArrangement = Arrangement.Top
        ) {
            HeaderSection()

            // Tambahkan Spacer untuk memberikan jarak antara Header dan Body
            Spacer(modifier = Modifier.height(70.dp)) // Sesuaikan tinggi Spacer sesuai kebutuhan

            BodySection(
                onHalamanPelanggan = onHalamanPelanggan,
                onHalamanReservasi = onHalamanReservasi,
                onHalamanReview = onHalamanReview,
                onHalamanVilla = onHalamanVilla
            )
        }
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 48.dp))
            .background(color = Color.White)
            .padding(bottom = 25.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 1.dp, top = 25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.umy), // Ganti dengan logo Anda
                contentDescription = "App Logo",
                modifier = Modifier
                    .padding(5.dp)
                    .size(130.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "Villa App",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(Modifier.weight(1f))

        }
    }
}

@Composable
fun BodySection(
    onHalamanPelanggan: () -> Unit,
    onHalamanReservasi: () -> Unit,
    onHalamanReview: () -> Unit,
    onHalamanVilla: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(48.dp)) // Semua sudut membulat dengan radius 48.dp
            .background(Color.White.copy(alpha = 0.9f)) // Latar belakang putih dengan opacity 90%
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Teks "Silahkan pilih menu yang ingin Anda kelola"
            Text(
                text = "Silahkan pilih menu yang ingin Anda kelola",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            // Baris untuk menampilkan kotak-kotak menu
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    ManageBox(
                        title = "Pelanggan",
                        description = "Kelola Pelanggan",
                        backgroundColor = Color(0xFF6200EE), // Warna ungu
                        iconResource = R.drawable.person, // Ganti dengan ikon Pelanggan
                        onClick = { onHalamanPelanggan() }
                    )
                    Spacer(Modifier.height(16.dp))
                    ManageBox(
                        title = "Reservasi",
                        description = "Kelola Reservasi",
                        backgroundColor = Color(0xFF03DAC5), // Warna teal
                        iconResource = R.drawable.reservasi, // Ganti dengan ikon Reservasi
                        onClick = { onHalamanReservasi() }
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    ManageBox(
                        title = "Review",
                        description = "Kelola Review",
                        backgroundColor = Color(0xFFFFC107), // Warna amber
                        iconResource = R.drawable.review, // Ganti dengan ikon Review
                        onClick = { onHalamanReview() }
                    )
                    Spacer(Modifier.height(16.dp))
                    ManageBox(
                        title = "Villa",
                        description = "Kelola Villa",
                        backgroundColor = Color(0xFFE91E63), // Warna pink
                        iconResource = R.drawable.villa, // Ganti dengan ikon Villa
                        onClick = { onHalamanVilla() }
                    )
                }
            }
        }
    }
}

@Composable
fun ManageBox(
    title: String,
    description: String,
    backgroundColor: Color,
    iconResource: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Ikon di atas
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = "$title Icon",
                modifier = Modifier
                    .size(50.dp) // Sesuaikan ukuran ikon jika diperlukan
                    .clip(CircleShape)
            )

            // Judul di bawah ikon
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            // Deskripsi di bawah judul
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color(0xFFE0E0E0) // Warna abu-abu untuk deskripsi
            )
        }
    }
}