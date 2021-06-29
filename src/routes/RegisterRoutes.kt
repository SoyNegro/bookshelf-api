package com.thedarksideofcode.routes

import io.ktor.application.*
import io.ktor.routing.*



//TODO("Add version from Env var")
fun Route.bookRoute() {
    route("/api/v1/") {
        bookRouting()
        chapterRouting()
        catalogueRouting()
    }
}

fun Route.adminRoute(){
    route("/api/v1/admin/") {
        classificationRouting()
        userRouting()
    }
}

fun Application.registerAllRoutes() {
    routing {
        bookRoute()
        adminRoute()
    }
}
