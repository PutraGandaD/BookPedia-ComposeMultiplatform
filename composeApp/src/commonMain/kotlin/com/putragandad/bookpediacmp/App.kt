package com.putragandad.bookpediacmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
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
import com.putragandad.bookpediacmp.book.presentation.SelectedBookViewModel
import com.putragandad.bookpediacmp.book.presentation.book_detail.BookDetailAction
import com.putragandad.bookpediacmp.book.presentation.book_detail.BookDetailScreenRoot
import com.putragandad.bookpediacmp.book.presentation.book_detail.BookDetailViewModel
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
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    // reset the shared view model state
                    // when this screen reappear (for example back from detail -> main)
                    // using LaunchedEffect with key true
                    LaunchedEffect(true) {
                        selectedBookViewModel.onSelectBook(null)
                    }

                    BookListScreenRoot(
                        viewModel = viewModel ,
                        onBookClick = { book ->
                            selectedBookViewModel.onSelectBook(book)
                            navController.navigate(
                                Routes.BookDetail(book.id)
                            )
                        }
                    )
                }

                composable<Routes.BookDetail> {
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()
                    val viewModel = koinViewModel<BookDetailViewModel>()

                    LaunchedEffect(selectedBook) {
                        selectedBook?.let {
                            viewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
                        }
                    }

                    BookDetailScreenRoot(
                        viewModel = viewModel,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
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

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
) : T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )

}

