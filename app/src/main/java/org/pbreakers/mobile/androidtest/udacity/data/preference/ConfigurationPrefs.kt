package org.pbreakers.mobile.androidtest.udacity.data.preference

import android.content.Context

import com.google.gson.Gson
import org.pbreakers.mobile.androidtest.BuildConfig
import org.pbreakers.mobile.androidtest.udacity.extension.getDefault

import javax.inject.Inject

/*
*Created by Nhat.vo on 8/22/2019.
*/
class ConfigurationPrefs @Inject constructor(
    private val context: Context
) : IConfigurationPrefs {

    companion object {
        private const val KEY_API_TOKEN = "KEY_API_TOKEN"
        private const val KEY_DEFAULT_LANGUAGE = "KEY_DEFAULT_LANGUAGE"
        private const val KEY_WELCOMED = "KEY_WELCOMED"
    }

    private val pref by lazy {
        context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    /**
     * Token
     */
    override var token: String?
        get() = pref.getString(KEY_API_TOKEN, "")
        set(token) = pref.edit().putString(KEY_API_TOKEN, token).apply()

    override var language: String
        get() = pref.getString(KEY_DEFAULT_LANGUAGE, "").getDefault()
        set(language) = pref.edit().putString(KEY_DEFAULT_LANGUAGE, language).apply()

    override var welcomed: Boolean
        get() = pref.getBoolean(KEY_WELCOMED, false).getDefault()
        set(welcomed) = pref.edit().putBoolean(KEY_WELCOMED, welcomed).apply()



    /**
     * Clear data
     */
    override fun clear() {
        token = null
    }
}
