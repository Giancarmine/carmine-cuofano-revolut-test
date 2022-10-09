package com.example.service

import com.example.model.User
import com.example.repository.UserRepository
import java.time.LocalDate

class UserService {
    val userRepository = UserRepository()

    fun insert(username: String, dateOfBirth: LocalDate): Boolean {
        return userRepository.insertOrUpdate(User(username, dateOfBirth))
    }

    fun find(username: String): User {
        return userRepository.find(username)
    }
}