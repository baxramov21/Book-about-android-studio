package com.example.projectkarina.domain.usecases

import com.example.projectkarina.domain.repository.TextOperationsRepository

class CopyTextUseCase(private val repository: TextOperationsRepository) {
    operator fun invoke(startPosition: Int, endPosition: Int): String {
        return repository.copyText(startPosition, endPosition)
    }
}