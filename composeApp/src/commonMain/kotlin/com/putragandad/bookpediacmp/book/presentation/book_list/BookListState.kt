package com.putragandad.bookpediacmp.book.presentation.book_list

import com.putragandad.bookpediacmp.book.domain.Book
import com.putragandad.bookpediacmp.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "",
//    val searchResults: List<Book> = emptyList(),
    val searchResults: List<Book> = books,
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)

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