package com.pbreakers.mobile.androidtest.udacity.app.base.others

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.pbreakers.mobile.androidtest.R
import com.pbreakers.mobile.androidtest.udacity.data.preference.ConfigurationPrefs
import com.pbreakers.mobile.androidtest.udacity.data.preference.IConfigurationPrefs

abstract class BaseDialog<V : ViewDataBinding>(
    context: Context,
    @StyleRes style: Int = R.style.WideDialog
) :
    Dialog(context, style), IBaseDialogView {

    protected lateinit var dataBinding: V

    protected abstract fun getLayoutId(): Int
    protected abstract fun init()

    override fun getDisplayTitle(): String {
        return ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        dataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutId(), null, false)
        setContentView(dataBinding.root)
        init()
    }

    fun setFullScreen() {
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override val viewContext: Context
        get() = context

    override val configPrefs: IConfigurationPrefs
        get() = ConfigurationPrefs(viewContext)

    override fun onClose() {
        dismiss()
    }

    fun disableTouchOutside() {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}