package com.example.projectkarina.domain.repository

interface SearchDataRepository {
    fun searchData(word: String): Int
}