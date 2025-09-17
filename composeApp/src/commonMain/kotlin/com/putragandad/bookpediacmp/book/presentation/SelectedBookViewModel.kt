package com.putragandad.bookpediacmp.book.presentation

import androidx.lifecycle.ViewModel
import com.putragandad.bookpediacmp.book.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// only used as a "container" for passing Book object from main to detail screen
// since passing through a data class (bundle) through navargs is expensive and often
// cause the app to crash when the bundle is too big
class SelectedBookViewModel : ViewModel() {
    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = _selectedBook.asStateFlow()

    fun onSelectBook(book: Book?) {
        _selectedBook.value = book
    }
}