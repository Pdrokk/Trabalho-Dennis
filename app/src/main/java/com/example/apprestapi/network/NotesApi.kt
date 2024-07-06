package com.example.apprestapi.network

import com.example.apprestapi.model.Note
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotesApi {
    @GET("posts")
    suspend fun getNotes(): Response<List<Note>>

    @POST("posts")
    suspend fun addNote(@Body note: Note): Response<Note>
}
