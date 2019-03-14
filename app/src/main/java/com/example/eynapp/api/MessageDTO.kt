package com.example.eynapp.api

import com.google.gson.annotations.SerializedName

class MessageDTO {

    @SerializedName("title")
    var title: String = "title"

    @SerializedName("body")
    lateinit var body: String

    @SerializedName("userId")
    var userID: String = "user1"
}