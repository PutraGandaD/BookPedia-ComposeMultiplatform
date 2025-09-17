package com.putragandad.bookpediacmp.book.data.repository

import com.putragandad.bookpediacmp.book.data.network.RemoteBookDataSource
import com.putragandad.bookpediacmp.book.domain.Book
import com.putragandad.bookpediacmp.core.domain.DataError
import com.putragandad.bookpediacmp.core.domain.Result

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) {
    suspend fun searchBooks(query: String) : Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query)
    }
}