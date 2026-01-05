package com.example.first_app

import com.google.gson.annotations.SerializedName

data class SongModel(
    @SerializedName("title")
    val songTitle : String,

    @SerializedName("artists")
    val songArtists : String,

    @SerializedName("image")
    val songImage : String,

    @SerializedName("url")
    val songUrl : String,

    @SerializedName("id")
    val songId : String,


)
