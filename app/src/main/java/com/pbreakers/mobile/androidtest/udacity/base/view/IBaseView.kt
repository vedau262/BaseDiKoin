package com.pbreakers.mobile.androidtest.udacity.base.view

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import com.pbreakers.mobile.androidtest.udacity.app.base.viewmodel.BaseViewModel
import com.pbreakers.mobile.androidtest.udacity.app.model.error.ErrorMessage
import com.pbreakers.mobile.androidtest.udacity.data.preference.IConfigurationPrefs

interface IBaseView<T : BaseViewModel> {
    /**
     * Define the layout res id can be used to [Activity.setContentView]
     *
     * @return the layout res id
     */

    val mViewModel: T

    @LayoutRes
    fun getLayoutRes(): Int
    fun getViewModel(): T
    fun onDispatchTouchEvent()

    fun initView()
    fun initViewModel()
    fun showLoadingDialog()
    fun dismissLoadingDialog()
    fun handleError(errorMessage: ErrorMessage?)
    fun onHandleBackPressed()
    fun getToolbarTitle(): String?
    fun onEditTextChangedCallback(view: View, value: String?)
//    fun getCurrentFragment(id: Int): BaseFragment<*, *>?
//    fun getFragments(id: Int): MutableList<Fragment>?
    val configPrefs: IConfigurationPrefs
    val viewContext: Context
}