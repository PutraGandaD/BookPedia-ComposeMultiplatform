package com.putragandad.bookpediacmp.di

import com.putragandad.bookpediacmp.book.data.network.KtorRemoteBookDataSource
import com.putragandad.bookpediacmp.book.data.network.RemoteBookDataSource
import com.putragandad.bookpediacmp.book.data.repository.DefaultBookRepository
import com.putragandad.bookpediacmp.book.domain.BookRepository
import com.putragandad.bookpediacmp.book.presentation.SelectedBookViewModel
import com.putragandad.bookpediacmp.book.presentation.book_detail.BookDetailViewModel
import com.putragandad.bookpediacmp.book.presentation.book_list.BookListViewModel
import com.putragandad.bookpediacmp.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

// provide platform specific dependency using expect/val
// concept introduced in kmp
expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) } // provide engine from platformModule above

    // bind interface (from domain layer) to actual implementation (from data layer)
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    // provide viewmodel with needed constructor
    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
}