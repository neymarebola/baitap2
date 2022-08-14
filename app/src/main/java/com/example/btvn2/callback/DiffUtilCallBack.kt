package com.example.btvn2.callback

import androidx.recyclerview.widget.DiffUtil
import com.example.btvn2.models.Item

class DiffUtilCallBack: DiffUtil.Callback {
    private var oldList = listOf<Item>()
    private var newList = listOf<Item>()

    constructor(old: List<Item>, new: List<Item>) {
        oldList = old
        newList = new
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].documentId != newList[newItemPosition].documentId -> {
                false
            }
            oldList[oldItemPosition].title != newList[newItemPosition].title -> {
                false
            }
            oldList[oldItemPosition].description != newList[newItemPosition].description -> {
                false
            }
            oldList[oldItemPosition].contentType != newList[newItemPosition].contentType -> {
                false
            }
            oldList[oldItemPosition].publishedDate != newList[newItemPosition].publishedDate -> {
                false
            }
            oldList[oldItemPosition].publisher != newList[newItemPosition].publisher -> {
                false
            }
            oldList[oldItemPosition].originUrl != newList[newItemPosition].originUrl -> {
                false
            }
            oldList[oldItemPosition].avatar != newList[newItemPosition].avatar -> {
                false
            }
            oldList[oldItemPosition].images != newList[newItemPosition].images -> {
                false
            }
            oldList[oldItemPosition].content != newList[newItemPosition].content -> {
                false
            }
            else -> true
        }

    }
}