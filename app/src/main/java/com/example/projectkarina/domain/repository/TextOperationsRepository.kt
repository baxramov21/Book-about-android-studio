package com.example.projectkarina.domain.repository

interface TextOperationsRepository {

    fun copyText(startPosition: Int, endPosition: Int): String

    fun getContent(partNumber: Int): String
}