package com.thedarksideofcode.interfaces

import java.time.LocalDateTime

interface IElement {
    val title: String
    val createdDate: LocalDateTime
    val updateDate: LocalDateTime
}

interface VisibleElement {
    val isVisible: Boolean
    val isNFSWContent: Boolean
    val isStrongLanguage: Boolean
    val isVip: Boolean
    val isSpoiler: Boolean
}

interface Catalogue : IElement {
    val bookRating: Double
    val chaptersCount: Int
    val commentsCount: Int
    val status: String
    val image: String
}



