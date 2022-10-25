package com.example

import com.example.model.dto.DateOfBirthDTO
import com.example.service.UserService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    private var testUser = "rvlTest"
    private var badTestUser = "rvlTest123"
    private var userService = UserService()

    @Test
    fun testGetUserWithNoUsersDataInDB() = testApplication {
        val response = client.get("/hello/$testUser")

        userService.deleteAll()
        assertEquals(HttpStatusCode.NoContent, response.status)
    }

    @Test
    fun testPutANewCustomerWithTodayBDay() = testApplication {
        val response = client.put("/hello/$testUser") {
            contentType(ContentType.Application.Json)
            setBody(DateOfBirthDTO("1995-05-22"))
        }

        assertEquals(HttpStatusCode.Created, response.status)
    }

    @Test
    fun testPutANewCustomerWithBadUsername() = testApplication {
        val response = client.put("/hello/$badTestUser") {
            contentType(ContentType.Application.Json)
            setBody(DateOfBirthDTO(LocalDate.now().toString()).toString())
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun testGetUserWithTodayBData() = testApplication {
        val response = client.get("/hello/$testUser")

        assertEquals(HttpStatusCode.OK, response.status)
    }
}