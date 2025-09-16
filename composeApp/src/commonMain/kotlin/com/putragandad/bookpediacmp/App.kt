package com.putragandad.bookpediacmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.putragandad.bookpediacmp.book.presentation.book_list.BookListScreenRoot
import com.putragandad.bookpediacmp.book.presentation.book_list.BookListViewModel

@Composable
fun App() {
    BookListScreenRoot(
        viewModel = remember { BookListViewModel() },
        onBookClick = {

        }
    )
}