package com.example.projectkarina.domain.usecases

import com.example.projectkarina.domain.repository.SearchDataRepository

class SearchDataUseCase(private val repository: SearchDataRepository) {
    operator fun invoke(word: String): Int {
        return repository.searchData(word)
    }
}