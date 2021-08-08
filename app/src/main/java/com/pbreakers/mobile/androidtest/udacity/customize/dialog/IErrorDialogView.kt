package com.pbreakers.mobile.androidtest.udacity.customize.dialog

import com.pbreakers.mobile.androidtest.udacity.app.base.others.IBaseDialogView

interface IErrorDialogView: IBaseDialogView {
    fun onErrorConfirmed()
}