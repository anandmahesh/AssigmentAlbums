package com.example.assignmentcrownstack.repos.network

import androidx.annotation.Keep
import com.example.assignmentcrownstack.repos.database.Album
import com.google.gson.annotations.SerializedName

@Keep
data class AlbumResponse (
    @SerializedName("resultCount")
    var resultCount:String? = null,
    @SerializedName("results")
    var results:List<Album>? = null,
)