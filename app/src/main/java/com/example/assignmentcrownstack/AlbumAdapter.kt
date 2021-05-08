package com.example.assignmentcrownstack

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmentcrownstack.repos.database.Album
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AlbumAdapter(private val context: Context) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    var albums: List<Album> = ArrayList()

    fun setData(data: List<Album>) {
        albums = data
        notifyDataSetChanged()
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAlbum: AppCompatImageView = itemView.findViewById(R.id.img_album)
        val tvCollectionName: AppCompatTextView = itemView.findViewById(R.id.tv_collection_name)
        val tvCountry: AppCompatTextView = itemView.findViewById(R.id.tv_country)
        val tvTrackName: AppCompatTextView = itemView.findViewById(R.id.tv_track_name)
        val tvReleasedDate: AppCompatTextView = itemView.findViewById(R.id.tv_released_date)
        val tvPrice: AppCompatTextView = itemView.findViewById(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val obj = albums[position]
        Glide.with(context)
            .load(obj.artworkUrl100)
            .centerCrop()
            .into(holder.imgAlbum)
        holder.tvCollectionName.text = "${obj.collectionName ?: ""} by ${obj.artistName ?: ""}"
        holder.tvCountry.text = obj.country ?: ""
        holder.tvTrackName.text = obj.trackName ?: ""
        obj.collectionPrice?.let {
            if (it.isNotEmpty()) {
                holder.tvPrice.text = "$ $it"
            } else {
                holder.tvPrice.text = ""
            }
        }
        obj.releaseDate?.let {
            if (it.isNotEmpty()) {
                val year = getDate(it)
                holder.tvReleasedDate.text = year
            } else {
                holder.tvReleasedDate.text = ""
            }
        }
    }

    private fun getDate(date: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")//2012-06-25T07:00:00Z
            val outputFormat = SimpleDateFormat("yyyy")
            val date: Date = inputFormat.parse(date)//
            val formattedDate: String = outputFormat.format(date)
            println(formattedDate) // prints 2018
            formattedDate
        } catch (e: Exception) {
            ""
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}