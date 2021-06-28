package com.thedarksideofcode.service

import com.thedarksideofcode.DB
import com.thedarksideofcode.models.Book
import com.thedarksideofcode.models.Chapter
import com.thedarksideofcode.models.Rate
import org.litote.kmongo.*

class BookService {

    suspend fun getBookByTitle(title: String): Book? {
        return DB.bookCollection.findOne(Book::title eq title)
    }

    suspend fun deleteBookByTitle(title: String) {
        DB.bookCollection.deleteOne(Book::title eq title)
    }

    suspend fun saveBook(book: Book) {
        DB.bookCollection.save(book)
    }

    suspend fun updateBook(book:Book){
        DB.bookCollection.updateOne(Book::title eq book.title,book)
    }

    // TODO("Get this done with a projection")
    suspend fun getChapterByTitle(bookTitle: String, chapterName: String): Chapter? {
        return DB.bookCollection.findOne(Book::title eq bookTitle)?.chapterList?.find { it.title == chapterName }
    }

    suspend fun saveChapter(bookTitle: String, chapter: Chapter){
        DB.bookCollection.updateOne(Book::title eq bookTitle, push(Book::chapterList, chapter))
    }

    suspend fun deleteChapterByTitle(bookTitle: String, chapterName: String){
        DB.bookCollection.updateOne(Book::title eq bookTitle, pullByFilter(Book::chapterList, Chapter::title eq chapterName))
    }

    suspend fun setBookRating(bookTitle:String, rate: Rate){
        DB.bookCollection.updateOne(Book::title eq bookTitle, push(Book::rateList,rate))
    }

    suspend fun deleteBookRating(bookTitle: String,userId:String){
        DB.bookCollection.updateOne(Book::title eq bookTitle, pullByFilter(Book::rateList,Rate::idRater eq userId))
    }

}
