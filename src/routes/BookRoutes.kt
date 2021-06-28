package com.thedarksideofcode.routes

import com.thedarksideofcode.models.Book
import com.thedarksideofcode.models.Chapter
import com.thedarksideofcode.models.Rate
import com.thedarksideofcode.service.BookService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.bookRouting() {
    val bookService by di().instance<BookService>()
    route("/book/") {
        get("{bookTitle}") {
            val bookTitle: String = call.parameters["bookTitle"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val book: Book = bookService.getBookByTitle(bookTitle) ?: return@get call.respondText(
                "Not book was found",
                status = HttpStatusCode.NotFound
            )
            call.respond(book)
        }
        //TODO("Handle error")
        post {
            val book: Book = call.receive()
            bookService.saveBook(book)
            call.respond(
                HttpStatusCode.Accepted
            )
        }
        put {
            val book: Book = call.receive()
            bookService.updateBook(book)
            call.respond(
                HttpStatusCode.Accepted
            )
        }
        delete("{bookTitle}") {
            val bookTitle: String =
                call.parameters["bookTitle"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            bookService.deleteBookByTitle(bookTitle)
            call.respond(HttpStatusCode.Accepted)
        }

        post("rate/{bookTitle}") {
            val bookTitle: String =
                call.parameters["bookTitle"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val rate = call.receive<Rate>()
            bookService.setBookRating(bookTitle, rate)
            call.respond(HttpStatusCode.Accepted)
        }
        delete("rate/{bookTitle/{userId}") {
            val bookTitle: String =
                call.parameters["bookTitle"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            val userId: String = call.parameters["userId"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            bookService.deleteBookRating(bookTitle, userId)
            call.respond(HttpStatusCode.Accepted)
        }

    }
}

fun Route.chapterRouting() {
    val bookService by di().instance<BookService>()
    route("/{bookTitle}/chapter/") {
        get("{chapterName}") {
            val bookId: String = call.parameters["bookTitle"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val chapterName: String =
                call.parameters["chapterName"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val chapter =
                bookService.getChapterByTitle(bookId, chapterName) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(chapter)

        }
        //TODO("Handle error")
        post {
            val bookTitle: String = call.parameters["bookTitle"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val chapter = call.receive<Chapter>()
            bookService.saveChapter(bookTitle, chapter)
            call.respond(HttpStatusCode.Accepted)
        }
        delete("{chapterName}") {
            val bookTitle: String =
                call.parameters["bookTitle"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            val chapterName: String =
                call.parameters["chapterName"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            bookService.deleteChapterByTitle(bookTitle, chapterName)
            call.respond(HttpStatusCode.Accepted)
        }
    }
}

