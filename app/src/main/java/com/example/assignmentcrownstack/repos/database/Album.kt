package com.example.assignmentcrownstack.repos.database

import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "albums")
data class Album(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id:Int=0,
    @SerializedName("collectionId")
    var collectionId: String = "",
    @SerializedName("artistName")
    var artistName: String? = null,
    @SerializedName("collectionName")
    var collectionName: String? = null,
    @SerializedName("trackName")
    var trackName: String? = null,
    @SerializedName("artworkUrl100")
    var artworkUrl100: String? = null,
    @SerializedName("collectionPrice")
    var collectionPrice: String? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("currency")
    var currency: String? = null,
    @SerializedName("releaseDate")
    var releaseDate: String? = null,
    @SerializedName("trackTimeMillis")
    var trackTimeMillis: String? = null,
    @SerializedName("primaryGenreName")
    var primaryGenreName: String? = null
)