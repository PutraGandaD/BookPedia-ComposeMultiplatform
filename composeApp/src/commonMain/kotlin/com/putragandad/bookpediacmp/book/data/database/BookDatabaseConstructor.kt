package com.putragandad.bookpediacmp.book.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("KotlinNoActualForExpect")
expect object BookDatabaseConstructor: RoomDatabaseConstructor<FavoriteBookDatabase> {
    override fun initialize(): FavoriteBookDatabase
}