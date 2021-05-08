package com.example.assignmentcrownstack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.assignmentcrownstack.repos.AlbumRepository
import com.example.assignmentcrownstack.repos.LocalRepo
import com.example.assignmentcrownstack.repos.RemoteRepo
import com.example.assignmentcrownstack.repos.database.Album
import com.example.assignmentcrownstack.repos.database.AlbumDatabase
import com.example.assignmentcrownstack.repos.network.Resource

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val albumRepository: AlbumRepository

    init {
        val albumDao = AlbumDatabase.getDatabase(application).albumDao()
        val localRepo = LocalRepo(albumDao)
        val remoteRepo = RemoteRepo()
        albumRepository = AlbumRepository(localRepo, remoteRepo)
    }

    fun fetchAllAlbums(): LiveData<Resource<List<Album>>> {
        return albumRepository.fetchAllAlbums(false)
    }

    fun refreshData(): LiveData<Resource<List<Album>>> {
        return albumRepository.fetchAllAlbums(true)
    }

}