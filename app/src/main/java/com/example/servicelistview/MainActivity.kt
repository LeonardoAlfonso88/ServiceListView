package com.example.servicelistview

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val TAG_LOGS = "EjemploService"
    lateinit var service: ApiService
    var listView: ListView? = null
    var adapter: PostsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<ApiService>(ApiService::class.java)
        getAllPosts(this)

    }

    fun getAllPosts(activity: Activity){
        //Recibimos todos los posts
        service.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                val listPosts: List<Post>? = response?.body()
                val listView: ListView = findViewById<ListView>(R.id.listView) as ListView
                adapter = PostsAdapter(activity, listPosts)
                listView.adapter = adapter
            }
            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }
}
