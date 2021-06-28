package com.thedarksideofcode.routes

import com.thedarksideofcode.service.CatalogService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.catalogueRouting() {
    val catalogService by di().instance<CatalogService>()
    route("/catalogue") {
        get("/byCreatedDate/{page}/{size}") {
            val page: Int = call.parameters["page"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val size: Int = call.parameters["size"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val result = catalogService.getCatalogOrderByCreatedDate(page, size)
            if (result != null) {
                call.respond(result)
            } else call.respond(HttpStatusCode.NotFound)
        }
        get("/byLastUpdate/{page}/{size}") {
            val page: Int = call.parameters["page"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val size: Int = call.parameters["size"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val result = catalogService.getCatalogOrderByLastUpdate(page, size)
            if (result != null) {
                call.respond(result)
            } else call.respond(HttpStatusCode.NotFound)
        }
        get("/byRating/{page}/{size}") {
            val page: Int = call.parameters["page"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val size: Int = call.parameters["size"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val result = catalogService.getCatalogOrderByRating(page, size)
            if (result != null) {
                call.respond(result)
            } else call.respond(HttpStatusCode.NotFound)
        }
        get("/byStatus/{status}/{page}/{size}") {
            val status: String = call.parameters["status"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val page: Int = call.parameters["page"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val size: Int = call.parameters["size"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val result = catalogService.getCatalogByStatus(status, page, size)
            if (result != null) {
                call.respond(result)
            } else call.respond(HttpStatusCode.NotFound)
        }
        get("/byChaptersCount/{page}/{size}") {
            val page: Int = call.parameters["page"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val size: Int = call.parameters["size"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val result = catalogService.getCatalogOrderByChaptersCount(page, size)
            if (result != null) {
                call.respond(result)
            } else call.respond(HttpStatusCode.NotFound)
        }
        get("/byViews/{page}/{size}") {
            val page: Int = call.parameters["page"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val size: Int = call.parameters["size"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val result = catalogService.getCatalogOrderByViews(page, size)
            if (result != null) {
                call.respond(result)
            } else call.respond(HttpStatusCode.NotFound)
        }
        get("/byClassificationName/{classificationName}/{page}/{size}") {
            val classificationName =
                call.parameters["classificationName"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val page: Int = call.parameters["page"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val size: Int = call.parameters["size"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val result = catalogService.getBooksByClassificationName(classificationName, page, size)
            if (result != null) {
                call.respond(result)
            } else call.respond(HttpStatusCode.NotFound)
        }
    }

}
