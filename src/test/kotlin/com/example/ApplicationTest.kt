package com.example

import com.example.plugins.DatabaseFactory
import com.example.service.UserService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Before
import java.time.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    private var testUser = "rvlTest"
    private var badTestUser = "rvlTest123"
    private var todayDate = LocalDate.now()
    private var notTodayDate = LocalDate.now().minusYears(18).minusWeeks(2)

    private var todayBody = """{"dateOfBirth": "$todayDate"}""".trimIndent()
    private var notTodayBody = """{"dateOfBirth": "$notTodayDate"}""".trimIndent()

    private var userService = UserService()

    @BeforeTest
    fun beforeStartTests() {
        DatabaseFactory.init()
    }

    @Test
    fun testGetUserWithNoUsersDataInDB() = testApplication {
        userService.deleteAll()
        val response = client.get("/hello/$testUser")

        assertEquals(HttpStatusCode.NoContent, response.status)
    }

    @Test
    fun testPutANewUserWithTodayBDay() = testApplication {
        userService.deleteAll()
        val response = client.put("/hello/$testUser") {
            contentType(ContentType.Application.Json)
            setBody(todayBody)
        }

        assertEquals(HttpStatusCode.Created, response.status)
    }

    @Test
    fun testGetUserWithTodayBData() = testApplication {
        userService.deleteAll()
        userService.insert(testUser, todayDate)
        val response = client.get("/hello/$testUser")

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testPutANewUserWithNotTodayBDay() = testApplication {
        userService.deleteAll()
        val response = client.put("/hello/$testUser") {
            contentType(ContentType.Application.Json)
            setBody(notTodayBody)
        }

        assertEquals(HttpStatusCode.Created, response.status)
    }

    @Test
    fun testGetUserWithNotTodayBData() = testApplication {
        userService.deleteAll()
        userService.insert(testUser, notTodayDate)
        val response = client.get("/hello/$testUser")

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testPutANewUserWithBadUsername() = testApplication {
        val response = client.put("/hello/$badTestUser") {
            contentType(ContentType.Application.Json)
            setBody(notTodayBody)
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
}