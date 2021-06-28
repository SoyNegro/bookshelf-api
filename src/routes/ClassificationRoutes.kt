package com.thedarksideofcode.routes

import com.thedarksideofcode.models.Classification
import com.thedarksideofcode.service.ClassificationService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.classificationRouting() {
    val classificationService by di().instance<ClassificationService>()
    route("/classification") {
        get("{id}") {
            val id: String = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val classification =
                classificationService.getClassificationById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(classification)

        }
        post {
           val classification: Classification = call.receive()
               classificationService.saveClassification(classification)
            call.respond(HttpStatusCode.Accepted)
        }
        put {
            val classification: Classification = call.receive()
            classificationService.updateClassificationById(classification)
            call.respond(HttpStatusCode.Accepted)
        }
        delete("{id}") {
          val id:String = call.parameters["id"]?: return@delete call.respond(HttpStatusCode.BadRequest)
            classificationService.deleteClassificationById(id)
            call.respond(HttpStatusCode.Accepted)
        }
    }
}