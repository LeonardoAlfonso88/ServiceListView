package com.example.servicelistview

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PostsAdapter(private val activity: Activity, postsList: List<Post>?) : BaseAdapter(){
    private var postsList = ArrayList<Post>()

    init {
        this.postsList = postsList as ArrayList<Post>
    }

    override fun getCount(): Int {
        return postsList.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        var vi: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        vi = inflater.inflate(R.layout.row_item, null)
        val userId = vi.findViewById<TextView>(R.id.userId)
        val title = vi.findViewById<TextView>(R.id.title)
        val body = vi.findViewById<TextView>(R.id.body)
        userId.text = postsList[i].userId.toString()
        title.text = postsList[i].title
        body.text = postsList[i].body
        return vi
    }
}