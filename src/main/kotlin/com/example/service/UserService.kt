package com.example.service

import com.example.model.User
import com.example.repository.UserRepository
import java.time.LocalDate

class UserService {
    private val userRepository = UserRepository()

    fun find(username: String): User {
        return userRepository.find(username)
    }

    fun insert(username: String, dateOfBirth: LocalDate): Boolean {
        userRepository.insert(User(username, dateOfBirth))
        return true
    }

    fun update(username: String, dateOfBirth: LocalDate): Boolean {
        userRepository.update(User(username, dateOfBirth))
        return true
    }

    fun insertOrUpdate(username: String, dateOfBirth: LocalDate): Boolean {
        userRepository.update(User(username, dateOfBirth))
        return true
    }
}