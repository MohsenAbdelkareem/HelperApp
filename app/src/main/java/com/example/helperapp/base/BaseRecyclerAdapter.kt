package com.example.helperapp.base

import android.content.Context

import androidx.recyclerview.widget.RecyclerView
import com.example.helperapp.listeners.OnItemClickListener

abstract class BaseRecyclerAdapter<Item, CustomItem>(protected var context: Context, private var list: MutableList<Item>) : RecyclerView.Adapter<BaseRecyclerViewHolder>() {
    private var item: CustomItem? = null
    private lateinit var itemClickListener: OnItemClickListener

    fun setCustomItem(item: CustomItem) {
        this.item = item
        notifyDataSetChanged()
    }

    fun insertAll(list: List<Item>) {
        clearAll()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun insertItem(position: Int, item: Item) {
        this.list.add(position, item)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        this.list.removeAt(position)
        notifyDataSetChanged()
    }

    fun updateItem(position: Int, item: Item) {
        this.list.removeAt(position)
        insertItem(position, item)
    }

    fun clearAll() {
        this.list.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}
