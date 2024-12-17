package com.example.api_project

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class Post(
    val userId:Int,
    val id:Int?=null,
    val title:String,
    val body:String
)
interface ApiService{
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @POST("posts")
    fun createPost(@Body post: Post):Call<Post>
}