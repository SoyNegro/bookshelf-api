package com.thedarksideofcode.models

data class UserConfiguration(
    val canSeeStrongContent:Boolean,
    val canSeeNFSWContent: Boolean,

)

data class User(
    val email:String,
    val password:String,
    val role:String,
)