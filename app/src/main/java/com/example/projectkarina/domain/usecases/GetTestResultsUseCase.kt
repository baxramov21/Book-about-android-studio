package com.example.projectkarina.domain.usecases

import com.example.projectkarina.domain.repository.TestsRepository

class GetTestResultsUseCase(private val repository: TestsRepository) {
    operator fun invoke(partNumber: Int): HashMap<Int,Int> = repository.getTestResults(partNumber)
}