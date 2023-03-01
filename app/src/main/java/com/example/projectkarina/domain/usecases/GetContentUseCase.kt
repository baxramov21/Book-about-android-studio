package com.example.projectkarina.domain.usecases

import com.example.projectkarina.domain.repository.TextOperationsRepository

class GetContentUseCase(private val repository: TextOperationsRepository) {
    operator fun invoke(partNumber: Int): String {
        return repository.getContent(partNumber)
    }
}