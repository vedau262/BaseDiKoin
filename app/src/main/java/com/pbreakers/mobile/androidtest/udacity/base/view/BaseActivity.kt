package com.pbreakers.mobile.androidtest.udacity.app.base.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pbreakers.mobile.androidtest.udacity.app.base.viewmodel.BaseViewModel
import javax.inject.Inject


/**
 * Created by Nhat.vo on 16/11/2020.
 */
abstract class BaseActivity<V : ViewDataBinding, T : BaseViewModel> :AppCompatActivity(),
    IBaseActivity<T> {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory



//    @Inject
//    lateinit var prefs: IConfigurationPrefs

//    override val configPrefs: IConfigurationPrefs
//        get() = prefs

    override val viewContext: Context
        get() = this

    override fun getViewModel(): T {
        return mViewModel
    }

//    private var loadingProgress: LoadingProgress? = null
//    private var errorDialog: ErrorDialog? = null
    private var handelClickBack: HandelClickBack? = null

    protected lateinit var dataBinding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        onChangeStatusBarColor()
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes())
        dataBinding.lifecycleOwner = this
        initViewModel()
        initView()
    }


    override fun onChangeStatusBarColor() {}

    override fun onFullScreen() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        onCheckNewIntent(intent)
    }

    /**
     * Init [View] components here. Such as set adapter for [RecyclerView], set listener
     * or anything else
     */
    @SuppressLint("SetTextI18n")
    override fun initView() {
//        loadingProgress = LoadingProgress(this)
    }

    override fun initViewModel() {
        mViewModel.apply {
            lifecycle.addObserver(this as LifecycleObserver)
           /* errorObs.observe(this@BaseActivity, Observer {
                it.message?.apply {
                    handleError(it)
                    resetErrorMessage()
                }
            })*/
           /* isLoadingObs.observe(this@BaseActivity, Observer {
                if (it) {
                    showLoadingDialog()
                } else {
                    dismissLoadingDialog()
                }
            })*/
        }
    }

    override fun getToolbarTitle(): String? = null

    override fun onCheckNewIntent(intent: Intent?) {}



    override fun onEditTextChangedCallback(view: View, value: String?) {}

    override fun showLoadingDialog() {
       /* if (loadingProgress == null) {
            loadingProgress = LoadingProgress(this)
        }
        loadingProgress?.let {
            if (!it.isShowing) {
                it.show()
            }
        }*/
    }

    override fun dismissLoadingDialog() {
        /*try {
            loadingProgress?.let {
                it.dismiss()
                loadingProgress = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
    }

    /*override fun handleError(errorMessage: ErrorMessage?) {
        errorMessage?.let {
            if (!it.message.isNullOrEmpty()) {
                when (it.error) {
                    CommonError.UNKNOWN -> errorMessage.message = it.message
                    CommonError.NETWORK_ERROR -> errorMessage.message =
                        getString(R.string.msg_no_network)

                    else -> {
                    }
                }
                // End hardcode

                onShowErrorDialog(errorMessage.message.getDefault())
            }
        }
    }*/

    /**
     * Show error dialog message*/

    override fun onShowErrorDialog(message: String) {
       /* if (errorDialog == null) {
            errorDialog = ErrorDialog(viewContext, message)
        }

        errorDialog?.apply {
            setMessage(message)
            if (!isShowing) {
                show()
            }
        }*/
    }

    /*override fun getCurrentFragment(id: Int): BaseFragment<*, *>? {
        supportFragmentManager.findFragmentById(id)?.childFragmentManager?.fragments?.let {
            if (it.isNotEmpty()) {
                return it[0] as? BaseFragment<*, *>
            }
        }
        return null
    }

    override fun getFragments(id: Int): MutableList<Fragment>? {
        return supportFragmentManager.findFragmentById(id)?.childFragmentManager?.fragments
    }*/

    override fun findNavController(): NavController? {
        getNavId()?.let {
            return (supportFragmentManager.findFragmentById(it) as NavHostFragment).navController
        }
        return null
    }

    override fun getNavId(): Int? = null

    override fun onHandleBackPressed() {
        handelClickBack?.onBack()
        onBackPressed()
    }

    fun handelBackListener(listener: HandelClickBack) {
        handelClickBack = listener
    }

    interface HandelClickBack {
        fun onBack()
    }

    override fun onStop() {
        super.onStop()
//        Utils.hideSoftKeyboard(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            /*loadingProgress?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }*/
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        Utils.hideSoftKeyboard(this)
    }

    private var isClick: Boolean = false
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        onDispatchTouchEvent()
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                isClick = true
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                if (isClick) {
                    val v = currentFocus
                    if (v != null && v is EditText && !v.javaClass.name.startsWith("android.webkit.")) {
                        val scrcoords = IntArray(2)
                        v.getLocationOnScreen(scrcoords)
                        val x = ev.rawX + v.getLeft() - scrcoords[0]
                        val y = ev.rawY + v.getTop() - scrcoords[1]
                        if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()
                        ) {
                            v.clearFocus()
                            Handler().postDelayed({
                                if (currentFocus !is EditText) {
//                                    v.hideKeyboard(this)
                                }
                            }, 50)
                        }
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onDispatchTouchEvent() {
    }

    private fun addIntentsToList(
        context: Context,
        list: MutableList<Intent>,
        intent: Intent
    ): MutableList<Intent> {
        val resInfo: List<ResolveInfo> =
            context.packageManager.queryIntentActivities(intent, 0)
        for (resolveInfo in resInfo) {
            val packageName = resolveInfo.activityInfo.packageName
            val targetedIntent = Intent(intent)
            targetedIntent.setPackage(packageName)
            list.add(targetedIntent)
        }
        return list
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.data?.apply {
                onUriResult(this, requestCode)
                return
            }

            data?.extras?.get("data")?.apply {
                onBitmapResult(this as? Bitmap, requestCode)
            }
        }
    }

    open fun onUriResult(uri: Uri, requestCode: Int) {}

    open fun onBitmapResult(bitmap: Bitmap?, requestCode: Int) {}

    open fun onChooseImage(requestCode: Int, data: Intent?) {}

}