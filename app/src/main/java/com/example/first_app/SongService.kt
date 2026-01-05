package com.example.first_app

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface SongService {
    @GET("searchSongs?q=malayalam")
//    suspend fun getSongs(): Response<List<SongModel>>

    fun getSongs(): Call<List<SongModel>>
// /searchSongs?q=<query>

}
