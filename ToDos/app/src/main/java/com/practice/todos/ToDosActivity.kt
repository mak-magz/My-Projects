package com.practice.todos

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.practice.todos.ui.dialogs.AddToDoCategoryDialog
import com.practice.todos.ui.theme.ToDosTheme
import com.practice.todos.ui.screens.home.HomeScreen

class ToDosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDosTheme {
                // A surface container using the 'background' color from the theme
                ToDosApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true)
@Composable
fun ToDosApp() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    val currentDestination = currentBackStack?.destination
    val currentScreen = toDosScreens.find { it.route === currentDestination?.route } ?: HomeScreen
    val canShowFabButton = currentScreen.title === "Home"

    // Dialog
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = currentScreen.title)
                },
                navigationIcon = {
                    IconButton(onClick = { /* navigate to note screen */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (canShowFabButton) {
                FloatingActionButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add notes icon")
                }
            }
        }
    ) { contentPadding ->
        val startDestination = HomeScreen.route
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            ToDosNavigation(
                navController = navController,
                startDestination = startDestination
            )

            AddToDoCategoryDialog(
                showDialog = showDialog
            ) {
                showDialog = false
            }
        }
    }
}