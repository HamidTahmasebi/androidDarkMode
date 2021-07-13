package com.agiletech.darkmodetest.ui.main

import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DarkModePreferenceRepository (private val sharedPreferences: SharedPreferences) {

    /**
     * 1 -> off or DarkModeSetting.OFF.preferenceVal
     *
     * 2 -> auto or DarkModeSetting.AUTO.preferenceVal
     *
     * 3 -> on or DarkModeSetting.ON.preferenceVal
     */
    private val nightMode: Int
        get() = sharedPreferences.getInt(PREFERENCE_NIGHT_MODE, PREFERENCE_NIGHT_MODE_DEF_VAL)

    /**
     * Observer of this LiveData can observe and
     * use AppCompatDelegate.setDefaultNightMode to
     * change their dark mode accordingly.
     *
     * 1 -> off or DarkModeSetting.OFF.preferenceVal
     *
     * 2 -> auto or DarkModeSetting.AUTO.preferenceVal
     *
     * 3 -> on or DarkModeSetting.ON.preferenceVal
     */
    private val _nightModeLive: MutableLiveData<Int> = MutableLiveData()
    val nightModeLive: LiveData<Int>
        get() = _nightModeLive

    var darkModeSetting: Int = 1
        get() = nightModeLive.value!!
        set(value) {
            val mode = when (value) {
                1 -> AppCompatDelegate.MODE_NIGHT_NO
                2 -> AppCompatDelegate.MODE_NIGHT_YES
                3 -> {
                    when {
                        isAtLeastP() -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                        isAtLeastL() -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                        else -> AppCompatDelegate.MODE_NIGHT_NO
                    }
                }
                else -> AppCompatDelegate.MODE_NIGHT_NO
            }

            sharedPreferences.edit().putInt(PREFERENCE_NIGHT_MODE, mode).apply()
            field = value
        }

    private val preferenceChangedListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            when (key) {
                PREFERENCE_NIGHT_MODE -> {
                    _nightModeLive.value = nightMode
                }
            }
        }

    init {
        // Init preference LiveData objects.
        _nightModeLive.value = nightMode
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangedListener)
    }

    companion object {
        private const val PREFERENCE_NIGHT_MODE = "dark_mode"
        private const val PREFERENCE_NIGHT_MODE_DEF_VAL = AppCompatDelegate.MODE_NIGHT_NO
    }

    private fun isAtLeastP() = Build.VERSION.SDK_INT >= 28
    private fun isAtLeastL() = Build.VERSION.SDK_INT >= 26
}
