package com.gonzalez.banshe66.astrodjangoinfoapp

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

// Main home screen with navigation drawer, top bar, and card buttons for Astro and Django sections.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var showInfoDialog by remember { mutableStateOf(false) }
    var showContactDialog by remember { mutableStateOf(false) }
    var showPreferences by remember { mutableStateOf(false) }
    val viewModel: PreferencesViewModel = viewModel()

    // Navigation drawer with menu options
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Menu",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    MenuItem(text = "Preferences", onClick = { showPreferences = true })
                    MenuItem(text = "Contact", onClick = { showContactDialog = true })
                    MenuItem(text = "Info", onClick = { showInfoDialog = true })
                }
            }
        }
    ) {
        // Scaffold with a top app bar and two clickable card items
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_settings_24),
                                contentDescription = "Open menu"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Card for Astro section
                CardItem(
                    title = "Astro",
                    imageRes = R.drawable.astro_logo,
                    onClick = {
                        context.startActivity(
                            Intent(context, AstroVideosActivity::class.java)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Card for Django section
                CardItem(
                    title = "Django",
                    imageRes = R.drawable.djangoproject_logo,
                    onClick = {
                        context.startActivity(
                            Intent(context, SecondaryActivity::class.java).putExtra("TOPIC", "Django")
                        )
                    }
                )
            }

            // Info dialog
            if (showInfoDialog) {
                AlertDialog(
                    onDismissRequest = { showInfoDialog = false },
                    confirmButton = {
                        TextButton(onClick = { showInfoDialog = false }) {
                            Text("OK")
                        }
                    },
                    title = { Text("AstroDjangoInfoApp") },
                    text = {
                        Text("Version 1.0\n\nThis app is designed to learn about the Astro and Django frameworks.")
                    }
                )
            }

            // Contact dialog
            if (showContactDialog) {
                AlertDialog(
                    onDismissRequest = { showContactDialog = false },
                    confirmButton = {
                        TextButton(onClick = { showContactDialog = false }) {
                            Text("OK")
                        }
                    },
                    title = { Text("Contact") },
                    text = {
                        Text("fgonzalezlopez@ivytech.edu")
                    }
                )
            }

            // Preferences dialog (editable user info)
            if (showPreferences) {
                AlertDialog(
                    onDismissRequest = { showPreferences = false },
                    confirmButton = {},
                    title = { Text("Preferences") },
                    text = {
                        Column {
                            OutlinedTextField(
                                value = viewModel.name,
                                onValueChange = { viewModel.name = it },
                                label = { Text("First Name") },
                                enabled = viewModel.isEditable,
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = viewModel.surname,
                                onValueChange = { viewModel.surname = it },
                                label = { Text("Last Name") },
                                enabled = viewModel.isEditable,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(onClick = { viewModel.saveData(); showPreferences = false }) {
                                    Text("Save")
                                }
                                Button(onClick = { viewModel.enableEdit() }) {
                                    Text("Edit")
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

// Reusable menu item for navigation drawer with divider and chevron indicator
@Composable
fun MenuItem(text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = ">",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
    }
}

// Reusable card component for home screen buttons (Astro and Django)
@Composable
fun CardItem(title: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Button(
                onClick = onClick,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
            ) {
                Text("Go to $title")
            }
        }
    }
}
