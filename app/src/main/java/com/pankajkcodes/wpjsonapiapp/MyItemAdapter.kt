package com.pankajkcodes.wpjsonapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import android.content.Intent




class MyItemAdapter : ListAdapter<ModelItem, MyItemAdapter.MyViewHolder>(DiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)


        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener { v ->
            val i = Intent(v.context, DetailsActivity::class.java)
            i.putExtra("title", item.title)
            i.putExtra("content", item.content)
            v.context.startActivity(i)
        }
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.post_title)
        val image = view.findViewById<ImageView>(R.id.post_image)

        fun bind(item: ModelItem) {
            title.text = item.title

        }

    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ModelItem>() {
        override fun areItemsTheSame(oldItem: ModelItem, newItem: ModelItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: ModelItem,
            newItem: ModelItem
        ): Boolean {
            return oldItem == newItem
        }

    }


}