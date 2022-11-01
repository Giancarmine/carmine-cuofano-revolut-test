package com.example.plugins

import com.example.model.dto.DateOfBirthDTO
import com.example.model.dto.ErrorDTO
import com.example.model.dto.HelloDTO
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
            if (username.chars().allMatch(Character::isLetter)) {
                val msg = userService.sayHello(username)

                if (msg != null) {
                    call.respond(HttpStatusCode.OK, HelloDTO(msg))
                } else {
                    call.respond(HttpStatusCode.NoContent)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, ErrorDTO("Only letters are allowed for username param"))
            }
        }

        put("/hello/{username}") {
            val userDto = call.receive<DateOfBirthDTO>()
            val dateOfBirth = LocalDate.parse(userDto.dateOfBirth)
            val username: String = call.parameters["username"]!!

            if (!username.chars().allMatch(Character::isLetter)) {
                call.respond(HttpStatusCode.BadRequest, ErrorDTO("Only letters are allowed for username param"))
            } else if (LocalDate.now().isAfter(dateOfBirth) || LocalDate.now().isEqual(dateOfBirth)) {
                call.respond(HttpStatusCode.BadRequest, ErrorDTO("dateOfBirth value should be before today date"))
            } else {
                if (userService.insertOrUpdate(username, dateOfBirth)) {
                    call.respond(HttpStatusCode.Created)
                } else {
                    call.respond(HttpStatusCode.OK)
                }
            }
        }
    }
}
