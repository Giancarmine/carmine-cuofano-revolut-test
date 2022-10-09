package com.example.service

import com.example.model.dao.User
import com.example.repository.UserRepository
import java.time.LocalDate
import java.time.temporal.ChronoUnit


class UserService {
    private val userRepository = UserRepository()

    fun insertOrUpdate(username: String, dateOfBirth: LocalDate): Boolean {
        val user = userRepository.find(username)

        return if (user == null) {
            userRepository.insert(User(username, dateOfBirth))
        } else {
            userRepository.update(User(username, dateOfBirth))
        } > 0
    }

    fun sayHello(username: String): String? {
        val user = userRepository.find(username) ?: return null

        val remainingDays = daysRemain(user.dateOfBirth)

        return if (remainingDays > 0) {
            "Hello, " + user.username + "! Your birthday is in " + remainingDays + " day(s)"
        } else {
            "Hello, " + user.username + "! Happy birthday!"
        }
    }

    private fun daysRemain(dayOfBirth: LocalDate): Int {
        val today = LocalDate.now()
        val age = ChronoUnit.YEARS.between(dayOfBirth, today)
        var nextBirthday = dayOfBirth.plusYears(age)

        if (nextBirthday.isBefore(today)) {
            nextBirthday = dayOfBirth.plusYears(age + 1)
        }

        val daysUntilNextBirthday = ChronoUnit.DAYS.between(today, nextBirthday)
        return Math.toIntExact(daysUntilNextBirthday)
    }

    private fun find(username: String): User? {
        return userRepository.find(username)
    }

    private fun insert(username: String, dateOfBirth: LocalDate): Boolean {
        return userRepository.insert(User(username, dateOfBirth)) > 0
    }

    private fun update(username: String, dateOfBirth: LocalDate): Boolean {
        return userRepository.update(User(username, dateOfBirth)) > 0
    }

    fun deleteAll() {
        return userRepository.deleteAll()
    }
}