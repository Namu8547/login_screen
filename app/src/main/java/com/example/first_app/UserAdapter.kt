package com.example.first_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class
UserAdapter(private val userList : List<UserModel>  ):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val userName : TextView = itemView.findViewById<TextView>(R.id.userName)
        val userEmail : TextView = itemView.findViewById<TextView>(R.id.userEmail)
        val userImage : ImageView = itemView.findViewById<ImageView>(R.id.userImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card_view, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.userName.text = user.name
        holder.userEmail.text = user.email

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(holder.userImage)

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}