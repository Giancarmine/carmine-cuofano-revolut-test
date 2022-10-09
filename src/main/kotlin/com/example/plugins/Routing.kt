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

            val msg = userService.sayHello(username)

            if (msg != null) {
                call.respond(HttpStatusCode.OK, msg)
            } else {
                call.respond(HttpStatusCode.NoContent)
            }
        }

        put("/hello/{username}") {
            val userDto = call.receive<BirthOfDateDTO>()
            val username: String = call.parameters["username"]!!

            if (userService.insertOrUpdate(username, LocalDate.parse(userDto.dateOfBirth))) {
                call.respond(HttpStatusCode.Created)
            } else {
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
