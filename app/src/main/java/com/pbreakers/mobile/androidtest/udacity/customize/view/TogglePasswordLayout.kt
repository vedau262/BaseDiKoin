package com.pbreakers.mobile.androidtest.udacity.customize.view;

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.databinding.ViewDataBinding
import com.pbreakers.mobile.androidtest.databinding.CustomTogglePasswordBinding
import com.pbreakers.mobile.androidtest.udacity.base.other.BaseCustomView
import com.pbreakers.mobile.androidtest.udacity.extension.getDefault

class TogglePasswordLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : BaseCustomView(context, attrs, defStyle) {

    lateinit var edt: EditText
    lateinit var binding: CustomTogglePasswordBinding

    override fun initViewDataBinging(inflater: LayoutInflater): ViewDataBinding {
        binding = CustomTogglePasswordBinding.inflate(inflater, this, true)
        return binding
    }

    fun onChangeToggleStatus() {
        val isSelected = binding.isShowPassword.getDefault()
        binding.isShowPassword = !isSelected

        edt.transformationMethod = if (isSelected) {
            PasswordTransformationMethod.getInstance()
        } else {
            HideReturnsTransformationMethod.getInstance()
        }
    }
}