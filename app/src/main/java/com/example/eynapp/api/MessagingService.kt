package com.example.andrey.lastfmapp.api

import com.example.eynapp.api.MessageDTO
import com.example.eynapp.api.MessageResponseDTO
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MessagingService {

    @POST("posts")
    fun sendMessage(@Body request: MessageDTO): Single<MessageResponseDTO>

}