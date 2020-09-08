package soko.ekibun.bangumi.plugin.noscore

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.view.View
import android.view.ViewTreeObserver

class Plugin : Service() {

    private fun findViewById(activity: Activity, idName: String): View? {
        return activity.findViewById(activity.resources.getIdentifier(idName, "id", activity.packageName))
    }

    private fun changeLp(view: View?) {
        view?.layoutParams?.width = 1
        view?.alpha = 0f
    }

    fun setUpPlugins(activity: Activity, context: Context) {
        if (activity.javaClass.name == "soko.ekibun.bangumi.ui.subject.SubjectActivity") {
            var onGlobalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener? = null
            onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
                val view = findViewById(activity, "detail_score") ?: return@OnGlobalLayoutListener
                changeLp(view)
                changeLp(findViewById(activity, "detail_score_count"))
                changeLp(findViewById(activity, "item_friend_score_label"))
                changeLp(findViewById(activity, "detail_friend_score"))
                activity.window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
            }
            activity.window.decorView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}