package com.junior.testepi.model

import androidx.room.Entity
import androidx.room.PrimaryKey


class Usuario(
    val id: Long,
    val email: String,
    val senha: String,
) {
}