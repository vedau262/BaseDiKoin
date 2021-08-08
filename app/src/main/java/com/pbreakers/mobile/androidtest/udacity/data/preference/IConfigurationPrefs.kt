package com.pbreakers.mobile.androidtest.udacity.data.preference

interface IConfigurationPrefs {
    var token: String?
    var language: String
    var welcomed: Boolean
    fun clear()
}