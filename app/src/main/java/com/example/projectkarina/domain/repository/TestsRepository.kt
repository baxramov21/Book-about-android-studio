package com.example.projectkarina.domain.repository

interface TestsRepository {

    fun startTest(partNumber: Int): HashMap<ArrayList<String>,
            HashMap<ArrayList<String>, ArrayList<String>>>

    fun getTestResults(partNumber: Int): HashMap<Int, Int>
}