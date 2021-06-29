package com.thedarksideofcode.models

import java.time.LocalDateTime

data class UserConfiguration(
    val allowSeeStrongContent:Boolean,
    val allowNFSWContent: Boolean,

)

//TODO("Bring bookmarks out to play")
data class User(
    val userName:String?,
    val email:String,
    val password:String,
    val role:String?,
    val bookmarks:MutableList<Bookmark>?,
    var lastConnection:LocalDateTime = LocalDateTime.now()
)
