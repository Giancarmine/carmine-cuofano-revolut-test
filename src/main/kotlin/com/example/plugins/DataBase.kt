package com.example.plugins

import com.example.model.dao.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        initDB()
        transaction {
            SchemaUtils.create(Users)
        }
    }

    private fun initDB() {
        val config = HikariConfig("/hikari.properties")
        config.addDataSourceProperty("password", System.getenv("SECRET_KEY_BASE"))
        val ds = HikariDataSource(config)
        Database.connect(ds)
    }
}