package com.example.projectkarina.domain.usecases

import com.example.projectkarina.domain.repository.TestsRepository

/**
 *  Why i made the return type  HashMap<ArrayList<String>,HashMap<ArrayList<String>, ArrayList<String>>>
 *  HashMap -> inside it there List of questions
 *  Then one more HashMap -> Inside which there are two lists:
 *  first: List of right answers
 *  second: List of wrong answers
 */

class StartTestUseCase(private val repository: TestsRepository) {
    operator fun invoke(partNumber: Int): HashMap<ArrayList<String>,
            HashMap<ArrayList<String>, ArrayList<String>>> {
        return repository.startTest(partNumber)
    }
}