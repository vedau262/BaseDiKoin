package com.pbreakers.mobile.androidtest.udacity.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pbreakers.mobile.androidtest.udacity.model.dao.UserDao
import com.pbreakers.mobile.androidtest.udacity.model.entity.GithubUser

@Database(entities = [GithubUser::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}