package com.example.projectkarina.domain.repository

interface LoginRepository {
    fun signUp(): Boolean

    fun login(): Boolean
}