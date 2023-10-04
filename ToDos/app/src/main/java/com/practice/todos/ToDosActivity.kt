package com.practice.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.practice.todos.ui.ToDosApp
import com.practice.todos.ui.theme.ToDosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDosActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDosTheme {
                ToDosApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun ToDosAppPreview() {
    ToDosTheme {
        // A surface container using the 'background' color from the theme
        ToDosApp()
    }
}