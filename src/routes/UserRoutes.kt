package com.thedarksideofcode.routes

import com.thedarksideofcode.service.UserService
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.userRouting(){
    val userService by di().instance<UserService>()
    route("/user/"){
        get("{username}") {

        }
        post {

        }
        put {

        }
        delete("{username}") {

        }
    }
}
