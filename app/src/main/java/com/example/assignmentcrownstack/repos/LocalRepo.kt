package com.example.assignmentcrownstack.repos

import com.example.assignmentcrownstack.repos.database.Album
import com.example.assignmentcrownstack.repos.database.AlbumDao

class LocalRepo(val albumDao: AlbumDao) {


    suspend fun insertAlbum(data: Album) {
        albumDao.insertAlbum(data)
    }

}