package com.example.btvn2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btvn2.R
import com.example.btvn2.models.MarkUp

class MarkupAdapter: RecyclerView.Adapter<MarkupAdapter.ViewHolder>() {
    var context: Context? = null
    var listMarkup = mutableListOf<MarkUp>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var href: TextView? = null

        init {
            href = itemView.findViewById(R.id.tv_href)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.markup_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var markUp = listMarkup[position]
        holder.href?.text = markUp.href
    }

    override fun getItemCount(): Int {
        return listMarkup.size
    }
}