package com.example.btvn2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.btvn2.R
import com.example.btvn2.models.Image

class ImageItemAdapter : RecyclerView.Adapter<ImageItemAdapter.ViewHolder> {
    private var context: Context? = null
    private var listImages = mutableListOf<Image>()

    constructor(ct: Context?, list: List<Image>) {
        context = ct
        listImages = list as MutableList<Image>
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var singleImage: ImageView? = null

        init {
            singleImage = itemView.findViewById(R.id.single_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.image_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var image = listImages?.get(position)
        var imageUrl = image?.href
        context?.let {
            Glide.with(it)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.img_placeholder)
                .into(holder.singleImage!!)
        }
    }

    override fun getItemCount(): Int {
        return listImages?.size!!
    }
}