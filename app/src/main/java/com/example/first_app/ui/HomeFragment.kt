package com.example.first_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.first_app.R
import com.example.first_app.RetrofitInstance
import com.example.first_app.SongAdapter
import com.example.first_app.SongModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment : Fragment( R.layout.fragment_home) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SongAdapter
    private val songList = mutableListOf<SongModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.songRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = SongAdapter(songList)
        recyclerView.adapter = adapter

        fetchSongs()
    }

    private fun fetchSongs(){
         RetrofitInstance.api.getSongs()
            .enqueue(object : Callback<List<SongModel>> {

                override fun onResponse(
                    call: Call<List<SongModel>>,
                    response: Response<List<SongModel>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        songList.clear()
                        songList.addAll(response.body()!!)
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<SongModel>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}