package com.example.repository

import com.example.model.User
import com.example.model.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserRepository {
    fun insertOrUpdate(user: User): Boolean {
        val retrievedUser: User = find(user.username)

//        val ret = retrievedUser != null ?
//            insert(user)
//            : update(user)

        return true
    }

    fun find(username: String): User {
        return transaction {
            Users.select { Users.username eq username }
                .map {
                    User(
                        it[Users.username],
                        it[Users.dateOfBirth]
                    )
                }.first()
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
            Users.update({ Users.username eq id }) {
                it[username] = user.username
                it[dateOfBirth] = user.dateOfBirth
            }
        }
    }
}