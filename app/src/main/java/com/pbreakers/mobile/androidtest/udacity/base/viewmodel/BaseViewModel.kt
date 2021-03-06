package com.pbreakers.mobile.androidtest.udacity.app.base.viewmodel

//import com.google.firebase.iid.FirebaseInstanceId
import android.util.Log
import androidx.lifecycle.*
import com.pbreakers.mobile.androidtest.udacity.app.model.error.ErrorMessage
import com.pbreakers.mobile.androidtest.udacity.utils.LoadingState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


abstract class BaseViewModel : ViewModel(), IBaseViewModel,
    LifecycleObserver {

    var compositeDisposable = CompositeDisposable()

    private var currentDisposable: Disposable? = null

    private val _loadingState = MutableLiveData<LoadingState>()
    override val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _isLoadingObs = MutableLiveData<Boolean>()
    override val isLoadingObs: LiveData<Boolean>
        get() = _isLoadingObs

    private val _errorObs = MutableLiveData<ErrorMessage>()
    override val errorObs: LiveData<ErrorMessage>
        get() = _errorObs

    protected var isUserVisible: Boolean = false

    override fun setLoadingState(loadingState: LoadingState) {
        _loadingState.postValue(loadingState)
    }

    override fun setLoading(boolean: Boolean) {
        _isLoadingObs.postValue(boolean)
    }

    override fun setErrorMessage(t: Throwable?, message: String?) {
        _errorObs.postValue(ErrorMessage(t, message))
    }

    override fun resetErrorMessage() {
        _errorObs.postValue(ErrorMessage())
    }

    override fun addDisposable(disposable: Disposable, isSaveDisposable: Boolean) {
        if (isSaveDisposable) {
            currentDisposable = disposable
        }
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(disposable)
    }

    open fun onRemoveCurrentDisposable() {
        currentDisposable?.apply {
            compositeDisposable.remove(this)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
        isUserVisible = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
        isUserVisible = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }



    open fun onApiError(t: Throwable) {
        setLoading(false)
        setErrorMessage(t)
        setLoadingState(LoadingState.error(throwable=t))
    }

    open fun onApiError(message: String?) {
        setLoading(false)
        setErrorMessage(message = message)
        Log.d("onApiError", "message: $message")
        setLoadingState(LoadingState.error(message= message))
    }
}