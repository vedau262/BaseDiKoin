package com.pbreakers.mobile.androidtest.udacity.customize.dialog

interface IActionDialogCustom {
    /**
     * Callback receiver action when user click button cancel
     */
    fun onCancelConfirmed()

    /**
     * Callback receiver action when user click button ok
     */
    fun onSuccessConfirmed()
}
