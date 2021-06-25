package org.pbreakers.mobile.androidtest.udacity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.pbreakers.mobile.androidtest.R
import org.pbreakers.mobile.androidtest.databinding.ActivityMainBinding
import org.pbreakers.mobile.androidtest.udacity.app.base.view.BaseActivity
import org.pbreakers.mobile.androidtest.udacity.data.preference.ConfigurationPrefs
import org.pbreakers.mobile.androidtest.udacity.data.preference.IConfigurationPrefs
import org.pbreakers.mobile.androidtest.udacity.utils.LoadingState
import org.pbreakers.mobile.androidtest.udacity.viewmodel.UserViewModel


class MainActivity : BaseActivity<ActivityMainBinding, UserViewModel>() {

    override val mViewModel: UserViewModel by viewModel()

    override fun initView() {
        super.initView()
        dataBinding.textView.text = "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
    }

    override fun initViewModel() {
        super.initViewModel()

        mViewModel.data.observe(this, Observer {
            // Todo: Populate the recyclerView here
            it.forEach { githubUser ->
                Toast.makeText(baseContext, githubUser.login, Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.loadingState.observe(this, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> Toast.makeText(baseContext, it.msg, Toast.LENGTH_SHORT).show()
                LoadingState.Status.RUNNING -> Toast.makeText(baseContext, "Loading", Toast.LENGTH_SHORT).show()
                LoadingState.Status.SUCCESS -> Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getLayoutRes(): Int  = R.layout.activity_main
}
