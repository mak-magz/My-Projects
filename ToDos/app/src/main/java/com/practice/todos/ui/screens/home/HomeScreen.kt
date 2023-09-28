package com.practice.todos.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.practice.todos.data.local.model.Category
import com.practice.todos.toDosScreens
import com.practice.todos.ui.dialogs.AddToDoCategoryDialog
import com.practice.todos.ui.theme.ToDosTheme
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.practice.todos.ToDoScreen
import com.practice.todos.ui.navigateSingleTopTo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = toDosScreens.find { it.route === currentDestination?.route } ?: com.practice.todos.HomeScreen

    // Dialog
    var showDialog by rememberSaveable { mutableStateOf(false) }

    val categories by viewModel.categories
    Scaffold(
        modifier = modifier,
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
            CategoryList(
                categories = categories,
                onItemClicked = {
                    Log.d("CATEGORY ID: ", it)
                    navController.navigate(ToDoScreen.route)
                }
            )
            AddToDoCategoryDialog(
                showDialog = showDialog,
                onDismissDialog = { showDialog = false },
                onClickSave = {
                    showDialog = false
                    viewModel.addCategory(it)
                }
            )
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

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    categories: List<Category> = emptyList(),
    onItemClicked: (categoryId: String) -> Unit
) {
    ToDosTheme {
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Fixed(1),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onClickCallback = { onItemClicked(category._id.toHexString()) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onClickCallback: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation( defaultElevation = 6.dp),
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClickCallback
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = category.title
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        category = Category().apply { title = "test" },
        onClickCallback = {}
    )
}