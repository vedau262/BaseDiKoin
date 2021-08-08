package com.pbreakers.mobile.androidtest.udacity.app.base.others

import android.content.Context
import com.pbreakers.mobile.androidtest.udacity.data.preference.IConfigurationPrefs

/**
 * Created by Nhat Vo on 25/11/2020.
 */
interface IBaseDialogView {
    val viewContext: Context
    val configPrefs: IConfigurationPrefs
    fun getDisplayTitle(): String
    fun onClose()
}