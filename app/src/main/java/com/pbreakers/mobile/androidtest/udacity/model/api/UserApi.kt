package com.pbreakers.mobile.androidtest.udacity.model.api

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred
import com.pbreakers.mobile.androidtest.udacity.model.entity.GithubUser
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    fun getAllAsync(): Deferred<List<GithubUser>>
}