package com.tutorial.myapplication

import android.app.Application
import android.content.Context
import com.tutorial.myapplication.data.local.CrimeDatabase


class CriminalIntentApplication : Application() {
    init {
        app = this
    }
    companion object {
        private lateinit var app: CriminalIntentApplication
        fun getAppContext(): Context =  app.applicationContext
    }
    override fun onCreate() {
        super.onCreate()
        CrimeDatabase.initialize(this)
    }
}