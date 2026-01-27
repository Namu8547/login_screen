package com.example.first_app.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.first_app.HomeActivity
import com.example.first_app.MainActivity
import com.example.first_app.R
import com.example.first_app.RetrofitInstance
import com.example.first_app.SongAdapter
import com.example.first_app.SongModel
import com.example.first_app.UserAdapter
import com.example.first_app.UserModel
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment : Fragment( R.layout.fragment_home) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
//    private lateinit var adapter: SongAdapter
//    private val songList = mutableListOf<SongModel>()
    private val userList = mutableListOf<UserModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        recyclerView = view.findViewById(R.id.songRecyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        adapter = SongAdapter(songList)
//        recyclerView.adapter = adapter

//        fetchSongs()

        recyclerView = view.findViewById(R.id.userRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserAdapter(userList)
        recyclerView.adapter = adapter
        fetchUsers()



         val logOutButton : ImageButton = view.findViewById(R.id.logoutButton)
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(context, MainActivity::class.java))
            requireActivity().finish()
        }
    }






//    private fun fetchSongs(){
//         RetrofitInstance.api.getSongs()
//            .enqueue(object : Callback<List<SongModel>> {
//
//                override fun onResponse(
//                    call: Call<List<SongModel>>,
//                    response: Response<List<SongModel>>
//                ) {
//                    if (response.isSuccessful && response.body() != null) {
//                        songList.clear()
//                        songList.addAll(response.body()!!)
//                        adapter.notifyDataSetChanged()
//                    }
//                }
//
//                override fun onFailure(call: Call<List<SongModel>>, t: Throwable) {
//                    t.printStackTrace()
//                }
//            })
//    }

    private fun fetchUsers(){
        RetrofitInstance.api.getUsers()
            .enqueue(object : Callback<List<UserModel>> {

                override fun onResponse(
                    call: Call<List<UserModel>>,
                    response: Response<List<UserModel>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        userList.clear()
                        userList.addAll(response.body()!!)
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}