package com.kareem.donotes

import android.app.Application
import android.content.Context

class NotesApplication : Application() {
    init {
        application = this
    }
    companion object {
        private lateinit var application: NotesApplication
        fun getApplicationContext(): Context = application.applicationContext
    }
}