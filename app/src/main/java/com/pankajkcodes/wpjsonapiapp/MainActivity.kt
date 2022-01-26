package com.pankajkcodes.wpjsonapiapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private val tag = "TAG"

    private lateinit var recyclerView: RecyclerView
    private val adapter = MyItemAdapter()
    val item = ArrayList<ModelItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recyclerView = findViewById(R.id.post_list_)

        extractPosts(resources.getString(R.string.api_url))
        val manager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = manager

        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = adapter


    }

    @SuppressLint("NotifyDataSetChanged")
    fun extractPosts(URL: String?) {
        // use volley to extract the data
        val queue = Volley.newRequestQueue(this)
        val request =
            JsonArrayRequest(Request.Method.GET, URL, null, {
                Log.d(tag, "onResponse: $it")
                for (i in 0 until it.length()) {


                    Log.d(tag, "onResponse: ${it.get(i)}")
                    val jsonObjectData: JSONObject = it.getJSONObject(i)
                    Log.d(tag, jsonObjectData.getString("date"))
                    Log.d(
                        tag, jsonObjectData.getJSONObject("title").getString("rendered")
                    )


                    val items = ModelItem(
                        jsonObjectData.getJSONObject("title").getString("rendered"),
                        jsonObjectData.getJSONObject("content").getString("rendered"),
                        jsonObjectData.getString("date"),
                        jsonObjectData.getJSONObject("excerpt").getString("rendered"),
                        "NO",
                        "No",
                        ""
//
                    )
                    item.add(items)

//
//                    var title: String,
//                    var content: String,
//                    var date: String,
//                    var excerpt: String,
//                    var tags: String,
//                    var categories: String,
//                    var feature_image: String
                }
                adapter.submitList(item)

            }
            ) { error ->
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        queue.add(request)
    }
}