package com.pbreakers.mobile.androidtest.udacity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async
import com.pbreakers.mobile.androidtest.udacity.app.base.viewmodel.BaseViewModel
import com.pbreakers.mobile.androidtest.udacity.data.preference.IConfigurationPrefs
import com.pbreakers.mobile.androidtest.udacity.model.repository.UserRepository
import com.pbreakers.mobile.androidtest.udacity.utils.LoadingState

class UserViewModel(private val userRepository: UserRepository,
                    private val prefs : IConfigurationPrefs
) : BaseViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    val data = userRepository.data

    init {
        fetchData()
    }

    private fun fetchData() {
        prefs.language = "xxxxxxxxxxxxxxxxxx"
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                userRepository.refresh()
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}