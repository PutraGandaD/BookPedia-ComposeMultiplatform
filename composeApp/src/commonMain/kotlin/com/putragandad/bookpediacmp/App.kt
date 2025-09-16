package com.putragandad.bookpediacmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.putragandad.bookpediacmp.book.presentation.book_list.BookListScreenRoot
import com.putragandad.bookpediacmp.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    BookListScreenRoot(
        viewModel = remember { BookListViewModel() },
        onBookClick = {

        }
    )
}

@Preview
@Composable
fun PreviewMainApp() {
    MaterialTheme {
        App()
    }
}

