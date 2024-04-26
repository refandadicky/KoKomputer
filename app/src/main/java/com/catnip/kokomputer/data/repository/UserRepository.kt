package com.catnip.kokomputer.data.repository

import com.catnip.kokomputer.data.datasource.user.UserDataSource

interface UserRepository {
    fun isUsingDarkMode(): Boolean
    fun setUsingDarkMode(isUsingDarkMode: Boolean)
}

class UserRepositoryImpl(private val dataSource: UserDataSource) : UserRepository {
    override fun isUsingDarkMode(): Boolean {
        return dataSource.isUsingDarkMode()
    }

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        return dataSource.setUsingDarkMode(isUsingDarkMode)
    }

}