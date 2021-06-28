package com.thedarksideofcode.models

import com.thedarksideofcode.interfaces.IElement
import com.thedarksideofcode.interfaces.VisibleElement
import dev.coffeecult.workshopmanager.utils.TimeStampUtils
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

//Disclaimer, Announcement, Prologue, Content, Side_History, Epilogue

@Serializable
data class AuthorNote(
    val userName: String?,
    val content:String?,
    override val title: String = "Author Note",
    override val createdDate: LocalDateTime = LocalDateTime.now(),
    override val updateDate: LocalDateTime = LocalDateTime.now(),
    override var isVisible: Boolean = true,
    override var isNFSWContent: Boolean = false,
    override var isStrongLanguage: Boolean = false,
    override var isVip: Boolean = false,
    override var isSpoiler: Boolean = false
) : VisibleElement, IElement

@Serializable
data class Chapter(
    val chapterType: String?,
    val authorNote: AuthorNote?,
    val comments: MutableList<Comment>?,
    val chapterContent: String?,
    override val title: String,
    override val createdDate: LocalDateTime = LocalDateTime.now(),
    override val updateDate: LocalDateTime = LocalDateTime.now(),
    override var isVisible: Boolean = true,
    override var isNFSWContent: Boolean = false,
    override var isStrongLanguage: Boolean = false,
    override var isVip: Boolean = false,
    override var isSpoiler: Boolean = false
) : IElement, VisibleElement
