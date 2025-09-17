package com.putragandad.bookpediacmp

import android.app.Application
import com.putragandad.bookpediacmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            // you can specify specific module for only in Android here

            androidContext(this@BookApplication) //provide android context
        }
    }
}