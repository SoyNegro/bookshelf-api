package com.thedarksideofcode.models

import com.thedarksideofcode.interfaces.VisibleElement
import kotlinx.serialization.Serializable

// Tag, Genre

@Serializable
data class Classification(
    val classificationID: String?,
    val classificationName: String?,
    val classificationDescription: String?,
    val classificationType: String?,
    override var isVisible: Boolean = true,
    override var isNFSWContent: Boolean = false,
    override var isStrongLanguage: Boolean = false,
    override var isVip: Boolean = false,
    override var isSpoiler: Boolean = false
) : VisibleElement