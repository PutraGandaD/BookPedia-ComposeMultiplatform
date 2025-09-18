package com.putragandad.bookpediacmp.book.presentation.book_detail

import com.putragandad.bookpediacmp.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick: BookDetailAction
    data object OnFavoriteClick: BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}