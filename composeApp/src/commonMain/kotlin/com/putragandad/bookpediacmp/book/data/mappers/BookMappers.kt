package com.putragandad.bookpediacmp.book.data.mappers

import com.putragandad.bookpediacmp.book.data.dto.SearchResponseDto
import com.putragandad.bookpediacmp.book.domain.Book

fun SearchResponseDto.SearchedBookDto.toBook() : Book {
    return Book(
        id = key,
        title = title,
        imageUrl = if(mainCoverKey != null) {
            "https://covers.openlibrary.org/b/olid/${mainCoverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${alternativeCoverKey}-L.jpg"
        },
        authors = authorName ?: emptyList(),
        description = null,
        languages = language ?: emptyList(),
        firstPublishYear = firstPublishYear.toString(),
        averageRating = null,
        ratingCount = null,
        numPages = null,
        numEditions = 1
    )
}