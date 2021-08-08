package com.pbreakers.mobile.androidtest.udacity.extension

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.pbreakers.mobile.androidtest.udacity.app.App

/**
 * Created by Nhat Vo on 01/04/2021.
 */

fun Int.getString(): String {
    return App.instance.getString(this)
}

fun Int.getColor(): Int {
    return ContextCompat.getColor(App.instance.applicationContext, this)
}

fun Int.getDrawable(): Drawable? {
    return ContextCompat.getDrawable(App.instance.applicationContext, this)
}