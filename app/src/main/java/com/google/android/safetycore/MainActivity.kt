package com.google.android.safetycore

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = TextView(this).apply {
            text = "SafetyCore Blocker\n\nThis app does not uninstall, disable, or prevent Google SafetyCore updates.\n\nAndroid package-management restrictions prevent a normal third-party app from permanently blocking another app's installation or updates."
            textSize = 18f
            setPadding(32, 48, 32, 48)
        }
        setContentView(view)
    }
}
