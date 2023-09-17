package com.practice.mynote.ui.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practice.mynote.ui.theme.MyNoteTheme

@Composable
@OptIn(
	ExperimentalMaterial3Api::class
)
fun Home() {
	Scaffold(
		modifier = Modifier,
		topBar = {
			TopAppBar(
				title = {
					Text("Notes")
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
		},
		floatingActionButtonPosition = FabPosition.End,
		floatingActionButton = {
			FloatingActionButton(
				onClick = { /*TODO*/ }
			) {
				Icon(imageVector = Icons.Default.Add, contentDescription = "Add notes icon")
			}
		}
	) { contentPadding ->
		val titles = listOf(
			"First",
			"second",
			"third",
			"fourth",
			"fifth",
			"sixth"
		)

		NoteList(notes = titles, modifier = Modifier.padding(contentPadding))
	}
}

@Composable
fun NoteList(notes: List<String>, modifier: Modifier = Modifier) {
	LazyColumn(
		modifier = modifier
			.fillMaxWidth()
	) {
		items(items = notes) { title ->
			Note(title = title)
			Spacer(modifier = Modifier.size(10.dp))
		}
	}
}

@Composable
fun Note(title: String, modifier: Modifier = Modifier) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.background(color = MaterialTheme.colorScheme.onPrimary)
			.padding(5.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Box(
			modifier = Modifier
				.size(50.dp)
				.background(Color.Red)
				.clip(RectangleShape)
		)
		Spacer(modifier = Modifier.padding(end = 5.dp))
		Column {
			Text(text = title)
			Text(text = "date")
		}
	}
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MyNoteAppDarkPreview() {
	MyNoteTheme {
		Home()
	}
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun MyNoteAppPreview() {
	MyNoteTheme {
		Home()
	}
}