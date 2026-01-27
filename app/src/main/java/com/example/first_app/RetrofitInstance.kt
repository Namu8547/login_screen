package com.example.first_app

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// for define BASE URL and initiate retrofit

class RetrofitInstance {

    //Companion object consists of Singleton objects basically like static keywords

    companion object {

        val mainUrl = "https://api.escuelajs.co/api/v1/"
        val api: UserService by lazy {
            Retrofit.Builder()
                .baseUrl(mainUrl)
                .addConverterFactory(
                    GsonConverterFactory.create(GsonBuilder().create())
                )
                .build()
                .create(UserService::class.java)
        }

//        val mainUrl ="https://api.jiosaavn.com/"
//        val api: SongService by lazy {
//            Retrofit.Builder()
//                .baseUrl(mainUrl)
//                .addConverterFactory(
//                    GsonConverterFactory.create(GsonBuilder().create())
//                )
//                .build()
//                .create(SongService::class.java)
//        }


//        fun getRetrofitInstance(): Retrofit {
//            return Retrofit.Builder()
//                .baseUrl(mainUrl)
//                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
//        }

    }
}