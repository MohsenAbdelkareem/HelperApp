package com.example.helperapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.helperapp.listeners.OnItemClickListener


class BaseRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var clickableRootView: View? = null // this is used to change the default onItemClickListener


    fun setOnItemClickListener(itemClickListener: OnItemClickListener?) {
        if (clickableRootView != null) {
            clickableRootView!!.setOnClickListener { v ->
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, adapterPosition)
                }
            }
        } else {
            itemView.setOnClickListener(View.OnClickListener { v ->
                itemClickListener?.onItemClick(v, adapterPosition)
            })
        }
    }

    fun setClickableRootView(clickableRootView: View) {
        this.clickableRootView = clickableRootView
    }

    fun findViewById(viewId: Int): View {
        return itemView.findViewById(viewId)
    }
}
