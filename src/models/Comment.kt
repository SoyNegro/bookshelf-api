package com.thedarksideofcode.models

import com.thedarksideofcode.interfaces.IElement
import com.thedarksideofcode.interfaces.VisibleElement
import dev.coffeecult.workshopmanager.utils.TimeStampUtils
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Comment(
    val author: String?,
    val replies: MutableList<Comment>?,
    val content: String?,
    override val title: String,
    override val createdDate: LocalDateTime = LocalDateTime.now(),
    override var updateDate: LocalDateTime,
    override var isVisible: Boolean = true,
    override var isNFSWContent: Boolean = false,
    override var isStrongLanguage: Boolean = false,
    override var isVip: Boolean = false,
    override var isSpoiler: Boolean = false
) : IElement, VisibleElement
