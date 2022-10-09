package com.example

import com.example.plugins.DatabaseFactory
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.service.UserService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    private val userService = UserService()

    @Test
    fun config() = testApplication {
        application {
            DatabaseFactory.init()
            configureSerialization()
            configureRouting()
        }

    }

    @Test
    fun testNotUserPresent() = testApplication {
        client.get("/hello/rvlTest").apply {
            userService.deleteAll()
            assertEquals(HttpStatusCode.NoContent, status)
        }
    }

//    @Test
//    fun testAddAUser() = testApplication {
//        client.put("/hello/rvlTest") {
//            headers {
//                contentType(ContentType.Application.Json)
//                setBody(DateOfBirthDTO(LocalDate.now().toString()).toString())
//            }
//        }.apply {
//            println("body: " + body())
//            println("bodyAsText: " + bodyAsText())
//            assertEquals(HttpStatusCode.Created, status)
//        }
//    }
}