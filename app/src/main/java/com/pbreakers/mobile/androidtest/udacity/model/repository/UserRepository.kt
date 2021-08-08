package com.pbreakers.mobile.androidtest.udacity.model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.pbreakers.mobile.androidtest.udacity.model.api.UserApi
import com.pbreakers.mobile.androidtest.udacity.model.dao.UserDao
import com.pbreakers.mobile.androidtest.udacity.model.entity.GithubUser
import kotlinx.coroutines.Deferred

class UserRepository(private val userApi: UserApi, private val userDao: UserDao) {

    val data = userDao.findAll()

    suspend fun refresh() : List<GithubUser> {
        var users  : List<GithubUser> = mutableListOf()
        withContext(Dispatchers.IO){
            users = userApi.getAllAsync().await()
//            userDao.add(users)

        }
        return  users
    }
}