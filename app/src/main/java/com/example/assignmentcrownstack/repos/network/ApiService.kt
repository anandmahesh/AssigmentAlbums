package com.example.assignmentcrownstack.repos.network

import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("search/")
    suspend fun requestCollections(@Query("term") term:String):AlbumResponse?
}