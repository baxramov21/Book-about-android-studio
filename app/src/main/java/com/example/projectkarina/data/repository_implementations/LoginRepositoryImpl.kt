package com.example.projectkarina.data.repository_implementations

import com.example.projectkarina.domain.repository.LoginRepository

object LoginRepositoryImpl : LoginRepository {
    override fun signUp(): Boolean {
        return true
    }

    override fun login(): Boolean {
        return true
    }
}