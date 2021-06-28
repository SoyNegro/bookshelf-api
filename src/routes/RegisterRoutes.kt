package com.thedarksideofcode.routes

import io.ktor.application.*
import io.ktor.routing.*


fun Application.registerAllRoutes() {
    routing {
        apiBookRoute()
    }
}