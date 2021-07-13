package com.agiletech.darkmodetest

import android.app.Application
import android.content.Context
import com.agiletech.darkmodetest.ui.main.DarkModePreferenceRepository
import timber.log.Timber

/**
 * App.kt
 *
 * Created on 04/06/2020
 */
class App : Application() {

    lateinit var preferenceRepository: DarkModePreferenceRepository

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val where = "com.agiletch.testDarkMode.preferences"
        preferenceRepository = DarkModePreferenceRepository(getSharedPreferences(where, Context.MODE_PRIVATE))

        applicationContext()
    }


}