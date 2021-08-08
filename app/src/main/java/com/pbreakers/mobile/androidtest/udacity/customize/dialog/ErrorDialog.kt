package com.pbreakers.mobile.androidtest.udacity.customize.dialog

import android.content.Context
import com.pbreakers.mobile.androidtest.R
import com.pbreakers.mobile.androidtest.databinding.DialogErrorLayoutBinding
import com.pbreakers.mobile.androidtest.udacity.app.base.others.BaseDialog

class ErrorDialog(
    context: Context,
    private var message: String?,
    private val textConfirm: String? = context.getString(R.string.btn_ok),
    private val onConfirmed: (() -> Unit)? = null
) : BaseDialog<DialogErrorLayoutBinding>(context), IErrorDialogView {

    private var isInit: Boolean = false

    override fun getLayoutId(): Int = R.layout.dialog_error_layout

    override fun init() {
        isInit = true
        dataBinding.apply {
            messageText = message
            buttonText = textConfirm
            listener = this@ErrorDialog
        }
    }

    override fun onErrorConfirmed() {
        dismiss()
        onConfirmed?.invoke()
    }

    fun setMessage(message: String) {
        this.message = message
        if (isInit) {
            dataBinding.messageText = message
        }
    }
}