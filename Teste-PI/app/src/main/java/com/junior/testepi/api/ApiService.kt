package com.junior.testepi.api

import com.junior.testepi.model.Postagem
import com.junior.testepi.model.Tema
import com.junior.testepi.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    @GET("tema")
    suspend fun listTema():Response<List<Tema>>

    @POST("postagem")
    suspend fun addPost(
        @Body postagem: Postagem
    ): Response<Postagem>

    @GET("postagem")
    suspend fun listPostagem(): Response<List<Postagem>>

    @PUT("postagem")
    suspend fun upDatePost(
        @Body postagem: Postagem
    ): Response<Postagem>
}