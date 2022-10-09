package com.example

import com.example.plugins.DatabaseFactory
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory

fun main() {
    val configName = "application.conf"
    val appEngineEnv = applicationEngineEnvironment {
        config = HoconApplicationConfig(ConfigFactory.load(configName))
        log = LoggerFactory.getLogger("server.application")
        module {
            DatabaseFactory.init()
            configureSerialization()
            configureRouting()
        }
        connector {
            host = config.property("server.host").getString()
            port = config.property("server.port").getString().toInt()
        }
    }

    embeddedServer(Netty, appEngineEnv).start(wait = true)
}