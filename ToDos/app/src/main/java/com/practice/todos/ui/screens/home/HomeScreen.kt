package com.practice.todos.ui.screens.home

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.practice.todos.toDosScreens
import com.practice.todos.ui.dialogs.AddToDoCategoryDialog

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    val currentDestination = currentBackStack?.destination
    val currentScreen = toDosScreens.find { it.route === currentDestination?.route } ?: com.practice.todos.HomeScreen

    // Dialog
    var showDialog by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier,
        topBar = { HomeTopAppBar(title = currentScreen.title) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            HomeFloatingButton { showDialog = true }
        }
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Text(text = "hello")
            AddToDoCategoryDialog(
                showDialog = showDialog
            ) {
                showDialog = false
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(title: String) {
    TopAppBar(
        title = {
            Text(text = title)
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
}

@Composable
fun HomeFloatingButton(onClickCallback: () -> Unit) {
    FloatingActionButton(
        onClick = onClickCallback
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add notes icon")
    }
}