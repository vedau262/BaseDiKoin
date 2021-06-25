package org.pbreakers.mobile.androidtest.udacity.app.base.view

import android.content.Intent
import androidx.navigation.NavController
import org.pbreakers.mobile.androidtest.R
import org.pbreakers.mobile.androidtest.udacity.app.base.viewmodel.BaseViewModel
import org.pbreakers.mobile.androidtest.udacity.base.view.IBaseView


/**
 * Created by Nhat Vo on 17/11/2020.
 */
interface IBaseActivity<T : BaseViewModel> : IBaseView<T> {
    fun onChangeStatusBarColor()
    fun onFullScreen()
    fun onCheckNewIntent(intent: Intent?)
    fun getNavId(): Int?
    fun findNavController(): NavController?
    fun onShowErrorDialog(message: String)

}