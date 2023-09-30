package com.practice.todos.ui.screens.login

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.5f)
                    .background(color = Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Login",
                    fontSize = 36.sp
                )
                Spacer(modifier = Modifier.padding(12.dp))
                OutlinedTextField(
                    modifier = Modifier,
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Email") }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    modifier = Modifier,
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Password") }
                )
                Spacer(modifier = Modifier.padding(16.dp))
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "LOGIN")
                }
            }
        }
    }
}


@Preview(showBackground = true, device = Devices.DEFAULT)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}