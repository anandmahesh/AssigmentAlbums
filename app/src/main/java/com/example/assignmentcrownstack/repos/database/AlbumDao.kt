package com.example.assignmentcrownstack.repos.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): List<Album>

    @Insert
    fun insertAlbum(data: Album)

    @Query("DELETE From albums")
    fun deleteAll()

}