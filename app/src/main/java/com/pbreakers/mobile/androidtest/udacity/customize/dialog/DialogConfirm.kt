package com.pbreakers.mobile.androidtest.udacity.customize.dialog

import android.content.Context
import android.widget.LinearLayout
import com.pbreakers.mobile.androidtest.R
import com.pbreakers.mobile.androidtest.databinding.FragmentDialogCustomBinding
import com.pbreakers.mobile.androidtest.udacity.app.base.others.BaseDialog
import com.pbreakers.mobile.androidtest.udacity.extension.visible

/**
 *
 *   DialogConfirm.kt
 *
 *   Created by ThangTX on 06/05/2021.
 *
 */
class DialogConfirm(
    context: Context,
    var titleDialog: String,
    var subTitleDialog: String,
    var textButtonLeft: String,
    var textButtonRight: String
) :
    BaseDialog<FragmentDialogCustomBinding>(context), IActionDialogCustom {

    var onCancelClickListener: (() -> Unit)? = null
    var onSuccessClickListener: (() -> Unit)? = null

    private var isInit: Boolean = false

    override fun init() {
        isInit = true
        dataBinding.apply {
            title = titleDialog
            subTitle = subTitleDialog
            textButtonLeft = this@DialogConfirm.textButtonLeft
            textButtonRight = this@DialogConfirm.textButtonRight
            listener = this@DialogConfirm
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_dialog_custom
    override fun onCancelConfirmed() {
        onCancelClickListener?.invoke()
        dismiss()
    }

    override fun onSuccessConfirmed() {
        onSuccessClickListener?.invoke()
        dismiss()
    }

    fun setMessage(title: String, subTitle: String,textButtonLeft: String,textButtonRight: String) {
        this.titleDialog = title
        this.subTitleDialog = subTitle
        this.textButtonLeft = textButtonLeft
        this.textButtonRight = textButtonRight
        if (isInit) {
            dataBinding.title = title
            dataBinding.subTitle = subTitle
            dataBinding.textButtonLeft = textButtonLeft
            dataBinding.textButtonRight = textButtonRight
        }
    }

    fun hideButtonLeft(hide: Boolean){
        dataBinding.btnCancel.visible(!hide)
        if(hide){
            (dataBinding.btnCancel.layoutParams as LinearLayout.LayoutParams).weight = 0F
            (dataBinding.btnSOS.layoutParams as LinearLayout.LayoutParams).weight = 2F
        }

    }

    fun hideButtonRight(hide: Boolean){
        dataBinding.btnSOS.visible(!hide)
        if(hide){
            (dataBinding.btnSOS.layoutParams as LinearLayout.LayoutParams).weight = 0F
            (dataBinding.btnCancel.layoutParams as LinearLayout.LayoutParams).weight = 2F
        }
    }

}
