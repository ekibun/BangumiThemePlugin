package soko.ekibun.bangumi.plugin.theme

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.view.View
import android.view.WindowManager

class Plugin : Service() {

    private fun updateTheme(activity: Activity) {
        activity.window.clearFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        )
        activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity.window.statusBarColor = Color.TRANSPARENT
    }

    fun setUpPlugins(activity: Activity, context: Context) {
        updateTheme(activity)
        activity.window.decorView.viewTreeObserver.addOnWindowFocusChangeListener {
            updateTheme(activity)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}