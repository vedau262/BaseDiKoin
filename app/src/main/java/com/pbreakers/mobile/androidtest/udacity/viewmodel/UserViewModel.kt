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
import com.pbreakers.mobile.androidtest.udacity.model.dao.UserDao
import com.pbreakers.mobile.androidtest.udacity.model.entity.GithubUser
import com.pbreakers.mobile.androidtest.udacity.model.repository.UserRepository
import com.pbreakers.mobile.androidtest.udacity.utils.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserViewModel(private val userRepository: UserRepository,
                    private val prefs : IConfigurationPrefs,
                    private val dao: UserDao
) : BaseViewModel() {

    val _data = MutableLiveData<List<GithubUser>>()
    val data : LiveData<List<GithubUser>>
        get() {
            return _data
        }

    init {
        fetchData()
    }

    private fun fetchData() {
        prefs.language = "xxxxxxxxxxxxxxxxxx"
//        setLoading(true)
        val job = viewModelScope.launch(Dispatchers.Main) {
            try {
                setLoadingState(LoadingState.LOADING)
                val userList  = userRepository.refresh()
//                dao.add(userList)
                setLoadingState(LoadingState.LOADED)
                _data.postValue(userList)
//                setLoading(false)

            } catch (e: Exception) {
                setLoadingState(LoadingState.error(message = e.message))
                onApiError(e.message)
            }
        }
    }
}