package com.example.repository

import com.example.model.dao.User
import com.example.model.dao.Users
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserRepository {
    fun find(username: String): User? {
        return transaction {
            Users.select { Users.username eq username }
                .map {
                    User(
                        it[Users.username],
                        it[Users.dateOfBirth]
                    )
                }.firstOrNull()
        }
    }

    fun insert(user: User): Int {
        return transaction {
            Users.insert {
                it[username] = user.username
                it[dateOfBirth] = user.dateOfBirth
            }
        }.insertedCount
    }

    fun update(user: User): Int {
        return transaction {
            Users.update({ Users.username eq user.username }) {
                it[username] = user.username
                it[dateOfBirth] = user.dateOfBirth
            }
        }
    }

    fun deleteAll() {
        return transaction {
            Users.deleteAll()
        }
    }
}