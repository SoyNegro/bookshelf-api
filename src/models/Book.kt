package com.thedarksideofcode.models

import com.thedarksideofcode.interfaces.Catalogue
import com.thedarksideofcode.interfaces.VisibleElement
import kotlinx.serialization.Serializable
import java.time.LocalDateTime


// Ongoing, Suspended, Finished

@Serializable
data class Rate(val idRater: String, val rate: Double)

@Serializable
data class Book(
    val authorID: String,
    var bookStatus: String = "Ongoing",
    val bookSlug: String?,
    val bookDescription: String?,
    val bookCover: Picture?,
    val classificationList: MutableList<Classification>?,
    val rateList: MutableList<Rate>?,
    val commentList: MutableList<Comment>?,
    val chapterList: MutableList<Chapter>?,
    val views:Int,
    override val title: String,
    override val createdDate: LocalDateTime = LocalDateTime.now(),
    override var updateDate: LocalDateTime = LocalDateTime.now(),
    override var isVisible: Boolean = true,
    override var isNFSWContent: Boolean = false,
    override var isStrongLanguage: Boolean = false,
    override var isVip: Boolean = false,
    override var isSpoiler: Boolean = false
) : Catalogue, VisibleElement {
    override val bookRating: Double
        get() = rateList?.map { it.rate }?.average()!!
    override val chaptersCount: Int
        get() = chapterList?.size!!
    override val commentsCount: Int
        get() = commentList?.size!!
    override val status: String
        get() = bookStatus
    override val image: String
        get() = bookCover?.getPictureAsEncodedString()!!
}


