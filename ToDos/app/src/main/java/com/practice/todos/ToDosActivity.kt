package com.practice.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.practice.todos.ui.ToDosApp
import com.practice.todos.ui.theme.ToDosTheme

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