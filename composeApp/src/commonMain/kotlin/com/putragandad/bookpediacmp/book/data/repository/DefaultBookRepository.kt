package com.putragandad.bookpediacmp.book.data.repository

import com.putragandad.bookpediacmp.book.data.mappers.toBook
import com.putragandad.bookpediacmp.book.data.network.RemoteBookDataSource
import com.putragandad.bookpediacmp.book.domain.Book
import com.putragandad.bookpediacmp.book.domain.BookRepository
import com.putragandad.bookpediacmp.core.domain.DataError
import com.putragandad.bookpediacmp.core.domain.Result
import com.putragandad.bookpediacmp.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) : BookRepository {
    override suspend fun searchBooks(query: String) : Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query)
            .map { listBook ->
                listBook.results.map {
                    it.toBook()
                }
            }
    }
}