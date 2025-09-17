package com.putragandad.bookpediacmp.book.data.network

import com.putragandad.bookpediacmp.book.data.dto.SearchResponseDto
import com.putragandad.bookpediacmp.core.domain.DataError
import com.putragandad.bookpediacmp.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ) : Result<SearchResponseDto, DataError.Remote>
}