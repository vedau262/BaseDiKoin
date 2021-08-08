package com.pbreakers.mobile.androidtest.udacity.base.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.*
import android.graphics.Typeface
import android.view.inputmethod.InputMethodManager
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import com.pbreakers.mobile.androidtest.R
import timber.log.Timber
import java.util.*

object Utils {

    fun languageDefault(): String =
        if (Locale.getDefault().language != "en" && Locale.getDefault().language != "ar") "en"
        else Locale.getDefault().language


    fun hideSoftKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = activity.window.decorView
        }
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun getDefaultTypeFace(
        context: Context,
        @FontRes id: Int = R.font.proxima_nova_regular
    ): Typeface? = ResourcesCompat.getFont(context, id)

    fun copyText(context: Context, value: String) {
        val clip = ClipData.newPlainText("Copied Text", value)
        (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(
            clip
        )
    }


    private fun isServiceRunning(context: Context, mClass: String): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            return mClass == service.service.className
        }
        return false
    }
}