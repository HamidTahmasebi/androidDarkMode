package com.agiletech.darkmodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.agiletech.darkmodetest.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        (application as App).preferenceRepository.nightModeLive.observe(
                this,
                Observer { nightMode ->
                    nightMode?.let { delegate.localNightMode = it }
                }
        )


    }
}