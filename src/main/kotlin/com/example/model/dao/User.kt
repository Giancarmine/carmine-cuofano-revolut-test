package com.example.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate

data class User(
    val username: String,
    val dateOfBirth: LocalDate
)

object Users : Table() {
    val username = text("username")
    val dateOfBirth = date("dateOfBirth")
}