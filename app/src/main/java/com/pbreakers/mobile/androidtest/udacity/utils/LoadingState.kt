package com.pbreakers.mobile.androidtest.udacity.utils

import com.pbreakers.mobile.androidtest.udacity.app.model.error.ErrorMessage

@Suppress("DataClassPrivateConstructor")
data class LoadingState private constructor(val status: Status, val errorMessage: ErrorMessage? = null) {
    companion object {
        val LOADED = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.RUNNING)
//        fun error(msg: String?) = LoadingState(Status.FAILED, msg)
        fun error(throwable: Throwable? = null, message: String? = null) = LoadingState(Status.FAILED, ErrorMessage(throwable, message))

    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}