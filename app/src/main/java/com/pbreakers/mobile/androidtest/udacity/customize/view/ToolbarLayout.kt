package com.pbreakers.mobile.androidtest.udacity.customize.view;

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import com.pbreakers.mobile.androidtest.R
import com.pbreakers.mobile.androidtest.databinding.CustomToolbarBinding
import com.pbreakers.mobile.androidtest.udacity.base.other.BaseCustomView
import com.pbreakers.mobile.androidtest.udacity.base.view.IBaseView
import com.pbreakers.mobile.androidtest.udacity.extension.getString

class ToolbarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : BaseCustomView(context, attrs, defStyle) {

    lateinit var binding: CustomToolbarBinding

    override fun initViewDataBinging(inflater: LayoutInflater): ViewDataBinding {
        binding = CustomToolbarBinding.inflate(inflater, this, true)
        return binding
    }

    override fun onCreatedView() {
        super.onCreatedView()
        tag = R.string.tag_tool_bar_view.getString()
    }

    fun initToolbar(iView: IBaseView<*>) {
        binding.iView = iView
    }

    companion object {
        @BindingAdapter("app:initToolbar")
        @JvmStatic
        fun ToolbarLayout.initToolbar(iView: IBaseView<*>) {
            binding.iView = iView
        }
    }
}