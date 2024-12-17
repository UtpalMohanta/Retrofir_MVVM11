package com.example.api_project

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(private val posts:List<Post>):RecyclerView.Adapter<PostAdapter.PostViewHolder>(){
    class PostViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val title:TextView=view.findViewById(R.id.tvTitle)
        val boby:TextView=view.findViewById(R.id.tvBody)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.PostViewHolder, position: Int) {
        val post=posts[position]
        holder.title.text=post.title
        holder.boby.text=post.body
    }

    override fun getItemCount(): Int {
       return posts.size
    }

}