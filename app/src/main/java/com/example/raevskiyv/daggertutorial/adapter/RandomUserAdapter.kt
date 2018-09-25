package com.example.raevskiyv.daggertutorial.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.raevskiyv.daggertutorial.MainActivity
import com.example.raevskiyv.daggertutorial.R
import com.example.raevskiyv.daggertutorial.model.Result
import com.squareup.picasso.Picasso


/**
 * Created by Hari on 20/11/17.
 */

class RandomUserAdapter : RecyclerView.Adapter<RandomUserAdapter.RandomUserViewHolder>() {
    private var resultList: List<Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_random_user,
                parent, false)
        return RandomUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: RandomUserViewHolder, position: Int) {
        val result = resultList[position]
        holder.textView.text = String.format("%s %s", result.name?.first,
                result.name?.last)

        Picasso.with(holder.imageView.context)
                .load(result.picture?.large)
                .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun setItems(results: List<Result>) {
        resultList = results
        notifyDataSetChanged()
    }

    inner class RandomUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.name)
        var imageView: ImageView = itemView.findViewById(R.id.image)
    }
}