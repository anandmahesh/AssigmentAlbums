package com.example.assignmentcrownstack.repos

import com.example.assignmentcrownstack.repos.database.Album
import com.example.assignmentcrownstack.repos.network.RetrofitBuilder

class RemoteRepo() {

    suspend fun requestAllAlbums(): List<Album>? {
        return  RetrofitBuilder.apiService.requestCollections("Michael+jackson")?.results
    }

}