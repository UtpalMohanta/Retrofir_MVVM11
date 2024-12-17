package com.example.api_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView)
        button=findViewById(R.id.btnCreatePost)

        recyclerView.layoutManager=LinearLayoutManager(this)

        fetchPosts()
        button.setOnClickListener{
            createPost()
        }
    }



    private fun fetchPosts() {
        RetrofitClient.api.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    recyclerView.adapter = PostAdapter(posts)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("MainActivity", "Fail")
            }
        })
    }

    private fun createPost()
            {
                val newPost=Post(userId = 1, title = "New Post", body = "This is the content of the new post")
                RetrofitClient.api.createPost(newPost).enqueue(object :Callback<Post>{
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if (response.isSuccessful) {
                            val createdPost = response.body()
                            Log.d("MainActivity", "Created Post: $createdPost")
                        }

                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Log.e("MainActivity", "Error: ${t.message}")
                    }
        })

    }
}