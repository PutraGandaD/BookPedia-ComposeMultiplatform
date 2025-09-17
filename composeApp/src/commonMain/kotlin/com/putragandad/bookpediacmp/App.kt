package com.putragandad.bookpediacmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.putragandad.bookpediacmp.app.Routes
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
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Routes.BookGraph
        ) {
            navigation<Routes.BookGraph>(
                startDestination = Routes.BookList
            ) {
                composable<Routes.BookList> {
                    val viewModel = koinViewModel<BookListViewModel>()

                    BookListScreenRoot(
                        viewModel = viewModel ,
                        onBookClick = { book ->
                            navController.navigate(
                                Routes.BookDetail(book.id)
                            )
                        }
                    )
                }

                composable<Routes.BookDetail> { entry ->
                    val args = entry.toRoute<Routes.BookDetail>()

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Book detail screen. The id of book is ${args.id}")
                    }
                }
            }
        }
    }
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

