package com.putragandad.bookpediacmp.book.presentation.book_detail

import com.putragandad.bookpediacmp.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
