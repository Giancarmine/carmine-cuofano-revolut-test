package com.example.plugins

import com.example.model.dto.BirthOfDateDTO
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDate

fun Application.configureRouting() {
    val userService = UserService()

    routing {
        get("/hello/{username}") {
            val username: String = call.parameters["username"]!!
            println("Received $username")
            val result = userService.find(username)

            call.respond(HttpStatusCode.OK, result)
        }

        put("/hello/{username}") {
            val userDto = call.receive<BirthOfDateDTO>()

            val username: String = call.parameters["username"]!!
            userService.insert(username, LocalDate.parse(userDto.dateOfBirth))
            call.respond(HttpStatusCode.Created)
        }
    }
}
