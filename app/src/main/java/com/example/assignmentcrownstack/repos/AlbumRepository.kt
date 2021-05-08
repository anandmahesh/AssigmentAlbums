package com.example.assignmentcrownstack.repos

import androidx.lifecycle.liveData
import com.example.assignmentcrownstack.repos.database.Album
import com.example.assignmentcrownstack.repos.network.Resource
import kotlinx.coroutines.*

class AlbumRepository(val localRepo: LocalRepo, val remoteRepo: RemoteRepo) {

    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun fetchAllAlbums(isRefresh: Boolean) = liveData<Resource<List<Album>>>(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            var list = localRepo.albumDao.getAllAlbums()
            if (isRefresh) {
                localRepo.albumDao.deleteAll()
            }
            if (list.isEmpty() || isRefresh) {
                val result = scope.async {
                    try {
                        return@async remoteRepo.requestAllAlbums()
                    } catch (e: CancellationException) {
                        return@async ArrayList()
                    }
                }
                val dataAlbumList = result.await()
                if (!dataAlbumList.isNullOrEmpty()) {
                    for (albumData in dataAlbumList) {
                        insertAlbum(albumData)
                    }
                    list = localRepo.albumDao.getAllAlbums()
                    emit(Resource.success(data = list))
                } else {
                    emit(
                        Resource.error(
                            data = null,
                            message = "Something went wrong, Please try again"
                        )
                    )
                }
            } else {
                emit(Resource.success(data = list))
            }
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Something went wrong, Please try again"
                )
            )
        }
    }

    private suspend fun insertAlbum(data: Album) {
        localRepo.insertAlbum(data)
    }
}