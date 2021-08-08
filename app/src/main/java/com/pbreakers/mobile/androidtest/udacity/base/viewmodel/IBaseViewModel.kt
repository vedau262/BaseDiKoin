package com.pbreakers.mobile.androidtest.udacity.app.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pbreakers.mobile.androidtest.udacity.app.model.error.ErrorMessage
import com.pbreakers.mobile.androidtest.udacity.utils.LoadingState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable

interface IBaseViewModel {
    val loadingState: LiveData<LoadingState>
    val isLoadingObs: LiveData<Boolean>
    val errorObs: LiveData<ErrorMessage>

    fun setLoadingState(loadingState: LoadingState)
    fun setLoading(boolean: Boolean)
    fun setErrorMessage(t: Throwable? = null, message: String? = null)
    fun resetErrorMessage()

    fun getTempObservable(): Single<Any> {
        return Single.create {
            it.onSuccess(true)
        }
    }

    fun addDisposable(disposable: Disposable, isSaveDisposable: Boolean = false)
    /*fun requestPermission(
        rxPermissions: RxPermissions,
        permissions: Array<String>,
        onSuccess: ((Boolean) -> Unit)? = null
    )*/

//    fun checkPermission(rxPermissions: RxPermissions, permissions: Array<String>): Boolean


    fun onCreate()
    fun onStart()
    fun onPause()
    fun onResume()
    fun onStop()
    fun onDestroy()
}