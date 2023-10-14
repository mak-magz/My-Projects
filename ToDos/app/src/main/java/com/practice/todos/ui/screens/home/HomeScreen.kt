package com.practice.todos.ui.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.practice.todos.HomeScreen
import com.practice.todos.data.local.model.ToDos
import com.practice.todos.toDosScreens
import com.practice.todos.ui.dialogs.AddToDoCategoryDialog
import com.practice.todos.ui.navigateToDetails
import com.practice.todos.ui.theme.ToDosTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = toDosScreens.find { it.route === currentDestination?.route }
        ?: HomeScreen

    // Dialog
    var showDialog by rememberSaveable { mutableStateOf(false) }

    val categories by viewModel.todos
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopAppBar(
                title = currentScreen.title,
                drawerState = drawerState
            )
        },
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
                todos = categories,
                onItemClicked = {
                    Log.d("CATEGORY ID: ", it)
                    navController.navigateToDetails(it)
                },
                onDeleteIconClicked = {
                    viewModel.deleteToDos(it)
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
fun HomeTopAppBar(
    title: String,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
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
    todos: List<ToDos> = emptyList(),
    onItemClicked: (categoryId: String) -> Unit,
    onDeleteIconClicked: (todoId: String) -> Unit
) {
    ToDosTheme {
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Fixed(1),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(todos) { todo ->
                CategoryItem(
                    toDos = todo,
                    onClickCallback = { onItemClicked(todo._id.toHexString()) },
                    onDeleteCallback = { onDeleteIconClicked(todo._id.toHexString())}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    toDos: ToDos,
    onClickCallback: () -> Unit,
    onDeleteCallback: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClickCallback
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Min)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable(onClick = onClickCallback),
                text = toDos.title,
            )
            Icon(imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete todo",
                modifier = Modifier.clickable(onClick = onDeleteCallback),
                tint = Color.Red )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        toDos = ToDos().apply { title = "test" },
        onClickCallback = {},
        onDeleteCallback = {}
    )
}