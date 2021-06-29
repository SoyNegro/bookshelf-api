package com.thedarksideofcode

import com.fasterxml.jackson.databind.SerializationFeature
import com.thedarksideofcode.models.Book
import com.thedarksideofcode.models.Classification
import com.thedarksideofcode.routes.registerAllRoutes
import com.thedarksideofcode.service.bindService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.request.*
import org.kodein.di.ktor.di
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }

    install(AutoHeadResponse)

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ConditionalHeaders)

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("Bookshelf")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

//    install(CachingHeaders) {
//        options { outgoingContent ->
//            when (outgoingContent.contentType?.withoutParameters()) {
//                ContentType.Text.CSS -> CachingOptions(
//                    CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60),
//                    expires = null as? GMTDate?
//                )
//                else -> null
//            }
//        }
//    }

    install(DataConversion)

    install(DefaultHeaders) {
        header("Bookshelf", "Ktor based API") // will send this header with each response
    }

    install(HSTS) {
        includeSubDomains = true
    }

    // https://ktor.io/servers/features/https-redirect.html#testing
//    if (!testing) {
//        install(HttpsRedirect) {
//            // The port to redirect to. By default 443, the default HTTPS port.
//            sslPort = 443
//            // 301 Moved Permanently, or 302 Found redirect.
//            permanentRedirect = true
//        }
//    }

    di {
        bindService()

    }
    registerAllRoutes()
}

class DB {
    companion object {
        private val client = KMongo.createClient().coroutine
        private val database = client.getDatabase("bookshelf")
        val bookCollection = database.getCollection<Book>()
        val classificationCollection = database.getCollection<Classification>()
        val userCollection = database.getCollection<User>()

    }
}
