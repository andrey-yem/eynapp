package com.example.eynapp.api

import com.google.gson.annotations.SerializedName

class MessageResponseDTO {

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("body")
    lateinit var body: String

    @SerializedName("userId")
    lateinit var userID: String
}