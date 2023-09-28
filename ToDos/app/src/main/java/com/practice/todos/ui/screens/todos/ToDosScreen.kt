package com.practice.todos.ui.screens.todos

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.practice.todos.ToDoScreen
import com.practice.todos.toDosScreens
import com.practice.todos.ui.dialogs.AddToDoCategoryDialog
import com.practice.todos.ui.screens.home.CategoryList
import com.practice.todos.ui.screens.home.HomeFloatingButton
import com.practice.todos.ui.screens.home.HomeTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDosScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    categoryId: String
    ) {

    Log.d("CAT ID: ", categoryId)
    Scaffold(
        modifier = modifier,
        topBar = { ToDosTopAppBar(
            title = "ToDos",
            onBackIconClicked = { navController.popBackStack() }
        )}
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDosTopAppBar(
    title: String,
    onBackIconClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onBackIconClicked) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
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


@Preview(device = Devices.DEFAULT, showBackground = true)
@Composable
fun ToDoScreenPreview() {
    ToDosScreen(categoryId = "")
}
