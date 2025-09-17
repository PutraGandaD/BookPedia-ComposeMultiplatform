package com.putragandad.bookpediacmp.book.domain

import com.putragandad.bookpediacmp.core.domain.DataError
import com.putragandad.bookpediacmp.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String) : Result<List<Book>, DataError.Remote>
}