package com.putragandad.bookpediacmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.putragandad.bookpediacmp.book.data.network.KtorRemoteBookDataSource
import com.putragandad.bookpediacmp.book.data.repository.DefaultBookRepository
import com.putragandad.bookpediacmp.book.domain.Book
import com.putragandad.bookpediacmp.book.domain.BookRepository
import com.putragandad.bookpediacmp.book.presentation.book_list.BookListScreen
import com.putragandad.bookpediacmp.book.presentation.book_list.BookListScreenRoot
import com.putragandad.bookpediacmp.book.presentation.book_list.BookListState
import com.putragandad.bookpediacmp.book.presentation.book_list.BookListViewModel
import com.putragandad.bookpediacmp.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel = koinViewModel<BookListViewModel>()

    BookListScreenRoot(
        viewModel = viewModel ,
        onBookClick = {

        }
    )
}

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        authors = listOf("Putra Ganda Dewata"),
        description = "Description $it",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.67854,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}

@Preview
@Composable
fun PreviewMainApp() {
    MaterialTheme {
        BookListScreen(
            state = BookListState(
                searchResults = books
            ),
            onAction = {}
        )
    }
}

