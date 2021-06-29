package com.thedarksideofcode.service

import com.thedarksideofcode.DB
import com.thedarksideofcode.models.User
import org.litote.kmongo.eq

class UserService {
    suspend fun getUserByUsername(username:String): User? {
        return DB.userCollection.findOne(User::userName eq username)
    }

    suspend fun saveUser(user: User){
        DB.userCollection.insertOne(user)
    }

    suspend fun updateUserByUsername(user: User){
        DB.userCollection.updateOne(User::userName eq user.userName,user)
    }

    suspend fun deleteUserByUsername(username:String){
        DB.userCollection.deleteOne(User::userName eq username )
    }
}
