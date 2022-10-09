package com.example

import com.example.plugins.DatabaseFactory
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            DatabaseFactory.init()
            configureSerialization()
            configureRouting()
        }
        //client.get("/hello/pippo").apply {
        //    assertEquals(HttpStatusCode.OK, status)
            // assertEquals("Hello World!", bodyAsText())
        //}
    }
}