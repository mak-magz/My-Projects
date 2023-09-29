package com.practice.todos.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDoCategoryDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean = false,
    onDismissDialog: () -> Unit,
    onClickSave: (title: String) -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismissDialog,
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {
            var title by rememberSaveable { mutableStateOf("") }
            Card(
                modifier = modifier
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = "Enter category") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick =  onDismissDialog) {
                            Text(text = "CANCEL")
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Button(onClick = { onClickSave(title) }) {
                            Text(text = "ADD")
                        }
                    }
                }
            }
        }
    }
}

