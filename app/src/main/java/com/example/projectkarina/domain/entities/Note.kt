package com.example.projectkarina.domain.entities

import android.os.Parcelable

data class Note(
    var id: Int = UNDEFINED_ID,
    val title: String,
    val value: String
) : java.io.Serializable {
    companion object {
        const val UNDEFINED_ID = 0
    }
}