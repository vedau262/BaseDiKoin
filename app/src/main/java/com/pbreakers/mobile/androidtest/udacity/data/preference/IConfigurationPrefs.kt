package com.pbreakers.mobile.androidtest.udacity.data.preference


/**
 * Created by Nhat.vo on 4/16/2020.
 */

interface IConfigurationPrefs {
    var token: String?
    var language: String
    var welcomed: Boolean
    fun clear()
}