package com.putragandad.bookpediacmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.putragandad.bookpediacmp.book.presentation.book_list.components.BookSearchBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        BookSearchBar(
            onImeSearch = {},
            onSearchQueryChange = {},
            searchQuery = "",
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}