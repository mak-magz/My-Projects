package com.practice.mynote.ui.screens.my_note_app

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.practice.mynote.ui.theme.MyNoteTheme

@Composable
@OptIn(
	ExperimentalMaterial3Api::class
)
fun MyNoteApp() {
	Scaffold(
		modifier = Modifier,
		topBar = {
			TopAppBar(
				title = {
					Text("Notes")
				},
				navigationIcon = {
					IconButton(onClick = { /* doSomething() */ }) {
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
	) { contentPadding ->
		Column(modifier = Modifier.padding(contentPadding)) {

		}
	}
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MyNoteAppDarkPreview() {
	MyNoteTheme {
		MyNoteApp()
	}
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun MyNoteAppPreview() {
	MyNoteTheme {
		MyNoteApp()
	}
}