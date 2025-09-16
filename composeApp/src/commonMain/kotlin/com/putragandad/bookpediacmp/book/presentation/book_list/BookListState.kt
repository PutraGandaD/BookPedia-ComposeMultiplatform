package com.putragandad.bookpediacmp.book.presentation.book_list

import com.putragandad.bookpediacmp.book.domain.Book
import com.putragandad.bookpediacmp.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)