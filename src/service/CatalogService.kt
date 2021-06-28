package com.thedarksideofcode.service

import com.thedarksideofcode.DB
import com.thedarksideofcode.interfaces.Catalogue
import com.thedarksideofcode.models.Book
import com.thedarksideofcode.models.Classification
import com.thedarksideofcode.paginator.PageRequest
import com.thedarksideofcode.paginator.Paginator
import org.bson.conversions.Bson
import org.litote.kmongo.*

class CatalogService {

    suspend fun getCatalogOrderByCreatedDate(page: Int = 0, size: Int = 10): Paginator<Catalogue>? {
        return paginate(page,size, EMPTY_BSON,descending(Catalogue::createdDate))
        //DB.bookCollection.find().sort(descending(Book::createdDate)).toList()
    }

    suspend fun getCatalogOrderByLastUpdate(page: Int = 0, size: Int = 10): Paginator<Catalogue>? {
        return paginate(page,size, EMPTY_BSON,descending(Catalogue::updateDate))
        //DB.bookCollection.find().sort(descending(Book::updateDate)).toList()
    }

    suspend fun getCatalogOrderByRating(page: Int = 0, size: Int = 10): Paginator<Catalogue>? {
        return paginate(page,size, EMPTY_BSON,descending(Catalogue::bookRating))
        //DB.bookCollection.find().sort(descending(Book::bookRating)).toList()
    }

    suspend fun getCatalogByStatus(status: String, page: Int = 0, size: Int = 10): Paginator<Catalogue>? {
        return paginate(page,size, EMPTY_BSON,descending(Catalogue::updateDate))
        //DB.bookCollection.find().sort(descending(Book::updateDate)).toList()
    }

    suspend fun getCatalogOrderByChaptersCount(page: Int = 0, size: Int = 10): Paginator<Catalogue>? {
        return paginate(page,size, EMPTY_BSON,descending(Catalogue::chaptersCount))
        //DB.bookCollection.find().sort(descending(Book::chaptersCount)).toList()
    }

    suspend fun getCatalogOrderByViews(page: Int = 0, size: Int = 10): Paginator<Catalogue>? {
        return paginate(page,size, EMPTY_BSON,descending(Book::views))
        // DB.bookCollection.find().sort(descending(Book::views)).toList()
    }

    suspend fun getBooksByClassificationName(
        classificationName: String,
        page: Int = 0,
        size: Int = 10
    ): Paginator<Catalogue>? {
        return paginate(
            page, size, Book::classificationList / Classification::classificationType eq classificationName, ascending(
                Book::title
            )
        )
    }


    //TODO("Add constant pageNumber and pageSize ")
    private suspend fun paginate(page: Int, size: Int, filter: Bson, sort: Bson): Paginator<Catalogue>? {
        val documentsCount = DB.bookCollection.estimatedDocumentCount()
        val totalPages = (documentsCount / size) + 1
        return if (page <= totalPages) {
            val skippedDocuments: Int = page * size
            val result = DB.bookCollection.find(filter).skip(skippedDocuments).limit(size).sort(sort).toList()
            val prev = if (page > 0) page - 1 else null
            val next = if (result.isNotEmpty() && page <= totalPages) page + 1 else null
            val pageRequest = PageRequest(documentsCount, totalPages, next, prev)
            Paginator(result, pageRequest)

        } else null

    }

}
