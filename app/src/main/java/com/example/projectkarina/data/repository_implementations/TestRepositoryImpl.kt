package com.example.projectkarina.data.repository_implementations

import com.example.projectkarina.domain.repository.TextOperationsRepository

object TestRepositoryImpl : TextOperationsRepository {
    override fun copyText(startPosition: Int, endPosition: Int): String {
        TODO("Not yet implemented")
    }

    override fun getContent(partNumber: Int): String {
        TODO("Not yet implemented")
    }
}