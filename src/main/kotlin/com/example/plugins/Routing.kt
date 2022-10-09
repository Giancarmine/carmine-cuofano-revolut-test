package com.example.plugins

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
            val userDto = call.receiveText()

            val username: String = call.parameters["username"]!!
            userService.insert(username, LocalDate.now())
            call.respond(HttpStatusCode.Created)

//            val userDTO = call.receive<UserDTO>()
        }
    }
}
