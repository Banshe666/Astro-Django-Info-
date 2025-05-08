package com.gonzalez.banshe66.astrodjangoinfoapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gonzalez.banshe66.astrodjangoinfoapp.ui.theme.AstroDjangoInfoAppTheme

// Main entry point of the app. Sets the UI content and theme.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AstroDjangoInfoAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    HomeScreen()
                }
            }
        }
    }
}
