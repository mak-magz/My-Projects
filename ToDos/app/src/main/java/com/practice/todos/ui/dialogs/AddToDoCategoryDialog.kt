package com.practice.todos.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun AddToDoCategoryDialog(
    showDialog: Boolean = false,
    onDismissDialog: () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismissDialog,
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {
            AddToDoCategoryFormCard(
                closeDialog = onDismissDialog
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDoCategoryFormCard(
    modifier: Modifier = Modifier,
    closeDialog: () -> Unit
) {
    // TODO: Hoist state to a ViewModel
    var textValue by rememberSaveable { mutableStateOf("") }
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = textValue, onValueChange = { textValue = it },
                label = { Text(text = "Enter category") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick =  closeDialog) {
                    Text(text = "CANCEL")
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "ADD")
                }
            }
        }
    }
}

@Preview
@Composable
fun AddTodoCategoryDialogPreview() {
    AddToDoCategoryDialog(
        showDialog = true
    ) {}
}
